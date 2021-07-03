package com.dewen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.FsTask;
import com.dewen.entity.FsTaskUserRelateDTO;
import com.dewen.entity.FsUserAppTable;
import com.dewen.mapper.FsTaskMapper;
import com.dewen.mapper.FsTaskUserRelateMapper;
import com.dewen.mapper.FsUserAppMapper;
import com.dewen.mapper.FsUserAppTableMapper;
import com.dewen.service.IFsUserAppTableService;
import com.dewen.utils.CompareUtil;
import com.dewen.utils.HttpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户多维表格 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-06-29
 */
@Service
public class FsUserAppTableServiceImpl extends ServiceImpl<FsUserAppTableMapper, FsUserAppTable> implements IFsUserAppTableService {
    @Resource
    private FsUserAppMapper fsUserAppMapper;
    @Resource
    private FsUserAppTableMapper fsUserAppTableMapper;
    @Resource
    private FsTaskMapper fsTaskMapper;
    @Resource
    private FsTaskUserRelateMapper fsTaskUserRelateMapper;

    @Override
    @Transactional
    public void createFsUserAppTable() {
//        QueryWrapper<FsUserApp> fsUserAppTableMapper = new QueryWrapper<>();
//        // 未生成数据表的应用
//        fsUserAppTableMapper.lambda().eq(FsUserApp::getIsBuild, false);
//        List<FsUserApp> fsUserApps = fsUserAppMapper.selectList(fsUserAppTableMapper);
//
//        FsUserAppTable fsUserAppTable;
//        for (FsUserApp fsUserApp : fsUserApps) {
//            JSONObject reqContent = new JSONObject();
//            JSONObject child = new JSONObject();
//            // 创建数据表：发出的任务
//            child.put("name", "我发布的任务");
//            reqContent.put("table", child);
//            fsUserAppTable = new FsUserAppTable();
//            fsUserAppTable.setAppToken(fsUserApp.getAppToken());
//            fsUserAppTable.setCreateTime(new Date());
//            fsUserAppTable.setUserOpenId(fsUserApp.getUserOpenId());
//            fsUserAppTable.setIsBuildTask(true);
//            fsUserAppTable.setTableId(this.createTable(fsUserApp.getAppToken(), reqContent));
//            this.baseMapper.insert(fsUserAppTable);
//            // 创建数据表：我收到的任务
//            child.put("name", "我收到的任务");
//            reqContent.put("table", child);
//            fsUserAppTable = new FsUserAppTable();
//            fsUserAppTable.setIsBuildTask(false);
//            fsUserAppTable.setTableId(this.createTable(fsUserApp.getAppToken(), reqContent));
//            this.baseMapper.insert(fsUserAppTable);
//        }
    }

    /**
     * 创建数据表
     *
     * @param appToken
     * @param reqContent
     * @return
     */
    public String createTable(String appToken, JSONObject reqContent) {
        JSONObject res = HttpUtil.post2(String.format(ReqConst.ADD_TABLES, appToken), null, reqContent);
        if (0 == res.getInteger("code")) {
            JSONObject _obj = res.getJSONObject("data");
            return _obj.getString("table_id");
        } else {
            log.error(res.getString("msg"));
        }
        return null;
    }

    /**
     * 获取字段
     *
     * @param appToken
     * @param tableId
     * @return
     */
    public JSONArray getFields(String appToken, String tableId) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("parent_department_id", 0);
        JSONObject res = HttpUtil.get(String.format(ReqConst.FIELDS_OPERATION, appToken, tableId), null);
        if (0 == res.getInteger("code")) {
            return res.getJSONObject("data").getJSONArray("items");
        } else {
            log.error(res.getString("msg"));
        }
        return new JSONArray();

    }

    /**
     * 添加字段
     *
     * @param appToken
     * @param tableId
     * @return 字段id
     */
    public String addfields(String appToken, String tableId, JSONObject reqContent) {
        JSONObject res = HttpUtil.post2(String.format(ReqConst.FIELDS_OPERATION, appToken, tableId), null, reqContent);
        if (0 == res.getInteger("code")) {
            JSONObject _obj = res.getJSONObject("data").getJSONObject("field");
            return _obj.getString("field_id");
        } else {
            log.error(res.getString("msg"));
        }
        return null;
    }

    /**
     * 拉去我创建任务的数据-下发任务给到对应的人，并将任务同步到任务总表
     * 拉去数据
     * 插入主表
     */
    @Override
    @Transactional
    public void synTasks() {
        List<FsUserAppTable> userAppTableAll = fsUserAppTableMapper.selectList(null);
        // 总表
        FsUserAppTable totalTable = userAppTableAll.stream().filter(fsUserAppTable -> fsUserAppTable.getIsAdmin() == true).collect(Collectors.toList()).get(0);
        // 用户数据表
        List<FsUserAppTable> fsUserAppTables = userAppTableAll.stream().filter(fsUserAppTable -> fsUserAppTable.getIsAdmin() == false && StrUtil.isEmpty(fsUserAppTable.getPurpose())).collect(Collectors.toList());
        // 同步我创建任务
        for (FsUserAppTable userAppTable : fsUserAppTables) {
            synMyCreateTask(userAppTable, totalTable);
        }
        // 同步我的任务，如果修改将同步到各个数据
        for (FsUserAppTable userAppTable : fsUserAppTables) {
            synMyRecTask(userAppTable);
        }
    }


    /**
     * 同步我创建的任务
     *
     * @param userAppTable
     * @param totalTable
     */
    public void synMyCreateTask(FsUserAppTable userAppTable, FsUserAppTable totalTable) {
        FsTask fsTask;
        FsTask fsTaskDb;
        // 拉取数据
        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, userAppTable.getSendAppToken(), userAppTable.getSendTableId()), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                for (Object item : jsonArray) {
                    fsTask = new FsTask();
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    // rowId
                    fsTask.setId(itemObject.getString("id"));
                    fsTask.setTaskId(itemObject.getString("id"));
                    JSONObject fields = itemObject.getJSONObject("fields");
                    // 任务名称
                    String taskName = fields.getString("任务名称");
                    if (StrUtil.isNotEmpty(taskName))
                        fsTask.setTaskName(taskName);

                    // 任务类型
                    String taskType = fields.getString("任务类型");
                    if (StrUtil.isNotEmpty(taskType))
                        fsTask.setTaskType(taskType);

                    // 任务状态
                    String taskStatus = fields.getString("任务状态");
                    if (StrUtil.isNotEmpty(taskStatus))
                        fsTask.setTaskStatus(taskStatus);
                    // 发布时间
                    Timestamp releaseTime = fields.getTimestamp("发布时间");
                    if (releaseTime != null)
                        fsTask.setReleaseTime(releaseTime);
                    // 发布人
                    if (fields.getJSONArray("发布人") != null) {
//                        List<FsTask.Person> issuer = fields.getJSONArray("发布人").toJavaList(FsTask.Person.class);
                        fsTask.setIssuer(fields.getString("发布人"));
                    }
                    // 执行人
                    if (fields.getJSONArray("执行人") != null) {
//                        List<FsTask.Person> executor = fields.getJSONArray("执行人").toJavaList(FsTask.Person.class);
                        fsTask.setExecutor(fields.getString("执行人"));
                    }
                    fsTaskDb = fsTaskMapper.selectById(fsTask.getId());
                    if (fsTaskDb == null) {
                        // 需要插入数据库
                        fsTask.setCreateTime(new Date());
                        fsTask.setTaskClassify(0);
                        // 用户修改多维表格
                        fsTask.setAppToken(userAppTable.getSendAppToken());
                        fsTask.setTableId(userAppTable.getSendTableId());
                        fsTask.setUserOpenId(userAppTable.getUserOpenId());
                        fsTaskMapper.insert(fsTask);
                        if ("已发布".equals(fsTask.getTaskStatus())) {
                            // 给对应的人发发送任务
                            sendTaskRecUser(fsTask, itemObject);
                            // 插入到总表
                            insertTotalTask(fsTask, totalTable, itemObject);
                        }
                    } else {
                        // 需要更新
                        fsTask.setUpdateTime(new Date());
                        fsTaskMapper.updateById(fsTask);
                        this.updateMyCreateTask(fsTask, fsTaskDb, totalTable, itemObject);
                    }

                }
            }
        } else {
            log.error(res.toJSONString());
            throw new RuntimeException(res.getString("msg"));
        }

    }

    public void updateMyCreateTask(FsTask fsTask, FsTask fsTaskDb, FsUserAppTable totalTable, JSONObject itemObject) {
        itemObject.remove("id");
        // 未发布-->已发布
        if (!"已发布".equals(fsTaskDb.getTaskStatus()) && "已发布".equals(fsTask.getTaskStatus())) {
            // 给对应的人发发送任务
            sendTaskRecUser(fsTask, itemObject);
            // 插入到总表
            insertTotalTask(fsTask, totalTable, itemObject);
        } else if (!"已发布".equals(fsTaskDb.getTaskStatus()) && !"已发布".equals(fsTask.getTaskStatus())) {
            //不操作
        } else if ("已发布".equals(fsTaskDb.getTaskStatus()) && !"已发布".equals(fsTask.getTaskStatus())) {
            // 已发布-->未发布
            // 删除数据库及分飞书文档
            QueryWrapper<FsTask> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FsTask::getTaskId, fsTask.getTaskId()).ne(FsTask::getId, fsTask.getId());
            List<FsTask> fsTasks = fsTaskMapper.selectList(queryWrapper);
            for (FsTask task : fsTasks) {
                JSONObject res = HttpUtil.delete(String.format(ReqConst.MULI_TABLES_RECORD_UPDATE, task.getAppToken(), task.getTableId(), task.getId()), null, null);
                log.error(res.toJSONString());
            }
            fsTaskMapper.delete(queryWrapper);
        } else if ("已发布".equals(fsTaskDb.getTaskStatus()) && "已发布".equals(fsTask.getTaskStatus())) {
            // 更新线上
            QueryWrapper<FsTask> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FsTask::getTaskId, fsTask.getTaskId()).ne(FsTask::getId, fsTask.getId());
            List<FsTask> fsTasks = fsTaskMapper.selectList(queryWrapper);
            // 判断执行人有无变化
            List<String> executorIds = new ArrayList<>();
            List<String> dbExecutorIds = new ArrayList<>();
            if (StrUtil.isNotEmpty(fsTask.getExecutor()) && !Objects.equals("[]", fsTask.getExecutor())) {
                List<FsTask.Person> executor = JSONObject.parseArray(fsTask.getExecutor(), FsTask.Person.class);
                executorIds = executor.stream().map(FsTask.Person::getId).collect(Collectors.toList());
            }
            if (StrUtil.isNotEmpty(fsTaskDb.getExecutor()) && !Objects.equals("[]", fsTaskDb.getExecutor())) {
                List<FsTask.Person> dbExecutor = JSONObject.parseArray(fsTaskDb.getExecutor(), FsTask.Person.class);
                dbExecutorIds = dbExecutor.stream().map(FsTask.Person::getId).collect(Collectors.toList());
            }
            // 如果执行人没有改变就不更新执行人
            if (CompareUtil.cmp(executorIds, dbExecutorIds)) {
                JSONObject fields = itemObject.getJSONObject("fields");
                fields.remove("执行人");
            }
            for (FsTask task : fsTasks) {
                JSONObject res = HttpUtil.put(String.format(ReqConst.MULI_TABLES_RECORD_UPDATE, task.getAppToken(), task.getTableId(), task.getId()), null, itemObject);
                log.error(res.toJSONString());
            }
            // 更新数据库
            fsTaskMapper.update(fsTask.setId(null).setTaskClassify(null).setTaskId(null).setUpdateTime(new Date()), queryWrapper);
        }

    }

    /**
     * 插入数据到任务总表
     *
     * @param fsTask
     * @param totalTable
     * @param itemObject
     */
    public void insertTotalTask(FsTask fsTask, FsUserAppTable totalTable, JSONObject itemObject) {
        fsTask.setTaskId(fsTask.getId());
        fsTask.setId(this.writeRecord(totalTable.getSendAppToken(), totalTable.getSendTableId(), itemObject));
        fsTask.setTaskClassify(2);
        fsTask.setCreateTime(new Date());
        fsTask.setAppToken(totalTable.getSendAppToken());
        fsTask.setTableId(totalTable.getSendTableId());
        fsTask.setUserOpenId(totalTable.getUserOpenId());
        fsTaskMapper.insert(fsTask);
    }


    /**
     * 写数据到数据表
     *
     * @param appToken
     * @param tableId
     * @param itemObject
     * @return
     */
    public String writeRecord(String appToken, String tableId, JSONObject itemObject) {
        itemObject.remove("id");
        JSONObject insertRes = HttpUtil.post2(String.format(ReqConst.MULI_TABLES_RECORD, appToken, tableId), null, itemObject);
        if (0 == insertRes.getInteger("code")) {
            return insertRes.getJSONObject("data").getJSONObject("record").getString("id");
        } else {
            log.error(insertRes.getString("msg"));
            throw new RuntimeException(insertRes.getString("msg"));
        }
    }

    /**
     * 发送任务给指定的人
     *
     * @param fsTask
     * @param itemObject
     */
    public void sendTaskRecUser(FsTask fsTask, JSONObject itemObject) {
        fsTask.setTaskId(fsTask.getId());
        FsTask sendTask = new FsTask();
        // 根据任务类型读取配置表-->找到人员及对应的appTable信息
        List<FsTaskUserRelateDTO> fsTaskUserRelateDTOS = fsTaskUserRelateMapper.findFsTaskUserRelateDTOListByTaskType(fsTask.getTaskType());
        for (FsTaskUserRelateDTO fsTaskUserRelateDTO : fsTaskUserRelateDTOS) {
            BeanUtils.copyProperties(fsTask, sendTask);
            sendTask.setId(this.writeRecord(fsTaskUserRelateDTO.getRecAppToken(), fsTaskUserRelateDTO.getRecTableId(), itemObject));
            sendTask.setTaskClassify(1);
            sendTask.setCreateTime(new Date());
            sendTask.setAppToken(fsTaskUserRelateDTO.getRecAppToken());
            sendTask.setTableId(fsTaskUserRelateDTO.getRecTableId());
            sendTask.setUserOpenId(fsTaskUserRelateDTO.getOpenId());
            fsTaskMapper.insert(sendTask);
        }
    }

    /**
     * 同步我创建的任务
     * 判断执行人有变化，如果变化，就同步修改总表，发布任务的人表，所有收到该任务的我的任务列表
     *
     * @param userAppTable
     */
    public void synMyRecTask(FsUserAppTable userAppTable) {
        FsTask fsTask;
        FsTask fsTaskDb;
        // 拉取数据
        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, userAppTable.getRecAppToken(), userAppTable.getRecTableId()), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                for (Object item : jsonArray) {
                    fsTask = new FsTask();
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    // rowId
                    fsTask.setId(itemObject.getString("id"));
                    JSONObject fields = itemObject.getJSONObject("fields");
                    fsTaskDb = fsTaskMapper.selectById(fsTask.getId());
                    if (fsTaskDb == null)
                        continue;

                    boolean isChange = true;
                    // 执行人
                    if (fields.getJSONArray("执行人") != null) {
                        List<FsTask.Person> executor = fields.getJSONArray("执行人").toJavaList(FsTask.Person.class);
                        fsTask.setExecutor(fields.getString("执行人"));
                        List<String> executorIds = executor.stream().map(FsTask.Person::getId).collect(Collectors.toList());
                        // 比较执行人有无变化
                        List<FsTask.Person> dbExecutor = JSONObject.parseArray(fsTaskDb.getExecutor(), FsTask.Person.class);
                        if (dbExecutor != null) {
                            List<String> dbExecutorIds = dbExecutor.stream().map(FsTask.Person::getId).collect(Collectors.toList());
                            isChange = !CompareUtil.cmp(executorIds, dbExecutorIds);
                        }
                    } else if (fields.getJSONArray("执行人") == null && fsTaskDb.getExecutor() == null) {
                        isChange = false;
                    }
                    // 有改变
                    if (isChange) {
                        fsTask.setTaskId(fsTaskDb.getTaskId());
                        this.updateEexecutor(fsTask);
                    }
                }
            }
        } else {
            log.error(res.toJSONString());
            throw new RuntimeException(res.getString("msg"));
        }
    }

    /**
     * 修改 执行人
     */
    public void updateEexecutor(FsTask fsTask) {
        QueryWrapper<FsTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FsTask::getTaskId, fsTask.getTaskId());
        List<FsTask> fsTasks = fsTaskMapper.selectList(queryWrapper);
        JSONObject req = new JSONObject();
        req.put("fields", new JSONObject() {{
            if (StrUtil.isEmpty(fsTask.getExecutor())) {
                put("执行人", new ArrayList<FsTask.Person>());
            } else {
                put("执行人", JSONObject.parseArray(fsTask.getExecutor(), FsTask.Person.class));
            }
        }});
        for (FsTask task : fsTasks) {
            // 本条数据不用修改
            if (fsTask.getId().equals(task.getId()))
                continue;
            JSONObject res = HttpUtil.put(String.format(ReqConst.MULI_TABLES_RECORD_UPDATE, task.getAppToken(), task.getTableId(), task.getId()), null, req);
            log.error(res.toJSONString());
        }
        // 更新数据库
        if (StrUtil.isEmpty(fsTask.getExecutor())) {
            fsTaskMapper.update(new FsTask().setExecutor("[]").setUpdateTime(new Date()), queryWrapper);
        } else {
            fsTaskMapper.update(new FsTask().setExecutor(fsTask.getExecutor()).setUpdateTime(new Date()), queryWrapper);
        }
    }


    @Override
    @Transactional
    public void synAppTokenAndTaleInfo() {
        // 读取配置
        FsUserAppTable roleUserConfig = fsUserAppTableMapper.selectOne(new QueryWrapper<FsUserAppTable>().lambda().eq(FsUserAppTable::getPurpose, "collectInfo"));
        if (roleUserConfig == null)
            return;

        FsUserAppTable onLineFsUserAppTable;
        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, roleUserConfig.getSendAppToken(), roleUserConfig.getSendTableId()), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                // 查询数据库中那些openId存在
                List<FsUserAppTable> fsUserAppTableList = fsUserAppTableMapper.selectList(new QueryWrapper<FsUserAppTable>().last("where purpose is null"));
                Map<String, Integer> openIdMapId = new HashMap<>();
                for (FsUserAppTable fsUserAppTable : fsUserAppTableList) {
                    openIdMapId.put(fsUserAppTable.getUserOpenId(), fsUserAppTable.getId());
                }
                for (Object item : jsonArray) {
                    onLineFsUserAppTable = new FsUserAppTable();
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    // rowId
                    //onLineFsUserAppTable.setId(itemObject.getString("id"));
                    JSONObject fields = itemObject.getJSONObject("fields");
                    if (fields.size() == 0)
                        continue;
                    // 创建任务表格appToken
                    String sendAppToken = fields.getString("创建任务表格appToken");
                    if (StrUtil.isNotEmpty(sendAppToken))
                        onLineFsUserAppTable.setSendAppToken(sendAppToken);
                    // 创建任务表格tableId
                    String sendTableId = fields.getString("创建任务表格tableId");
                    if (StrUtil.isNotEmpty(sendTableId))
                        onLineFsUserAppTable.setSendTableId(sendTableId);
                    // 收到任务表格appToken
                    String recAppToken = fields.getString("收到任务表格appToken");
                    if (StrUtil.isNotEmpty(recAppToken))
                        onLineFsUserAppTable.setRecAppToken(recAppToken);
                    // 收到任务tableId
                    String recTableId = fields.getString("收到任务tableId");
                    if (StrUtil.isNotEmpty(recTableId))
                        onLineFsUserAppTable.setRecTableId(recTableId);
//                    String sendAppToken = fields.getString("创建任务表格");
//                    if (StrUtil.isNotEmpty(sendAppToken)) {
//                        onLineFsUserAppTable.setSendAppToken(sendAppToken.substring(sendAppToken.indexOf("base/") + 5, sendAppToken.indexOf("?table")));
//                        onLineFsUserAppTable.setSendTableId(sendAppToken.substring(sendAppToken.indexOf("table=") + 6, sendAppToken.indexOf("&view=")));
//                    }
//
//                    String recAppToken = fields.getString("收到任务表格");
//                    if (StrUtil.isNotEmpty(recAppToken)) {
//                        onLineFsUserAppTable.setRecAppToken(recAppToken.substring(recAppToken.indexOf("base/") + 5, recAppToken.indexOf("?table")));
//                        onLineFsUserAppTable.setRecTableId(recAppToken.substring(recAppToken.indexOf("table=") + 6, recAppToken.indexOf("&view=")));
//                    }

                    // 人员姓名
                    if (fields.getJSONArray("人员姓名") != null) {
                        List<FsTask.Person> executor = fields.getJSONArray("人员姓名").toJavaList(FsTask.Person.class);
                        if (executor != null && executor.size() > 0) {
                            onLineFsUserAppTable.setUserOpenId(executor.get(0).getId());
                            onLineFsUserAppTable.setUserName(executor.get(0).getName());
                            // 存在于数据库中
                            Integer id = openIdMapId.remove(executor.get(0).getId());
                            if (id == null) {
                                onLineFsUserAppTable.setCreateTime(new Date());
                                fsUserAppTableMapper.insert(onLineFsUserAppTable);
                            } else {
                                onLineFsUserAppTable.setId(id);
                                onLineFsUserAppTable.setUpdateTime(new Date());
                                fsUserAppTableMapper.updateById(onLineFsUserAppTable);
                            }
//                            if (openIdMapId.remove(executor.get(0).getId())) {
//
//                            }
                        }
                    }
                }
                // 删除线上删除得的数据
                openIdMapId.forEach((k, v) -> {
                    fsUserAppTableMapper.deleteById(v);
                });
            } else {
                log.error(res.toJSONString());
                throw new RuntimeException(res.getString("msg"));
            }

        }

    }

    public static void main(String[] args) {
        String url = "https://jrla61gzs4.feishu.cn/base/bascnXLfe1L46f0chx1hCIh24tT?table=tblnQbypE8J64Xmw&view=vewKL2Eo0w";
        String s = url.substring(url.indexOf("base/") + 5, url.indexOf("?table"));
        String s2 = url.substring(url.indexOf("table=") + 6, url.indexOf("&view="));
        System.out.println(s);
        System.out.println(s2);


    }
}
