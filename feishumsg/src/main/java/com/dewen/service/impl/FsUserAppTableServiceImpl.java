package com.dewen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.FsTask;
import com.dewen.entity.FsUserApp;
import com.dewen.entity.FsUserAppTable;
import com.dewen.mapper.FsTaskMapper;
import com.dewen.mapper.FsUserAppMapper;
import com.dewen.mapper.FsUserAppTableMapper;
import com.dewen.service.IFsUserAppTableService;
import com.dewen.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private FsUserAppTableMapper fsUserAppQueryWrapper;
    @Resource
    private FsTaskMapper fsTaskMapper;

    @Override
    @Transactional
    public void createFsUserAppTable() {
        QueryWrapper<FsUserApp> fsUserAppQueryWrapper = new QueryWrapper<>();
        // 未生成数据表的应用
        fsUserAppQueryWrapper.lambda().eq(FsUserApp::getIsBuild, false);
        List<FsUserApp> fsUserApps = fsUserAppMapper.selectList(fsUserAppQueryWrapper);

        FsUserAppTable fsUserAppTable;
        for (FsUserApp fsUserApp : fsUserApps) {
            JSONObject reqContent = new JSONObject();
            JSONObject child = new JSONObject();
            // 创建数据表：发出的任务
            child.put("name", "我发布的任务");
            reqContent.put("table", child);
            fsUserAppTable = new FsUserAppTable();
            fsUserAppTable.setAppToken(fsUserApp.getAppToken());
            fsUserAppTable.setCreateTime(new Date());
            fsUserAppTable.setUserOpenId(fsUserApp.getUserOpenId());
            fsUserAppTable.setIsBuildTask(true);
            fsUserAppTable.setTableId(this.createTable(fsUserApp.getAppToken(), reqContent));
            this.baseMapper.insert(fsUserAppTable);
            // 创建数据表：我收到的任务
            child.put("name", "我收到的任务");
            reqContent.put("table", child);
            fsUserAppTable = new FsUserAppTable();
            fsUserAppTable.setIsBuildTask(false);
            fsUserAppTable.setTableId(this.createTable(fsUserApp.getAppToken(), reqContent));
            this.baseMapper.insert(fsUserAppTable);
        }
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

    public void writeRecord() {

    }


    /**
     * 拉去我创建任务的数据-下发任务给到对应的人，并将任务同步到任务总表
     * 拉去数据
     * 插入主表
     */
    @Transactional
    public void synMyCreateTask() {
        List<FsUserAppTable> userAppTables = fsUserAppQueryWrapper.selectList(null);
        // 总表
        FsUserAppTable totalTable = userAppTables.stream().filter(fsUserAppTable -> fsUserAppTable.getIsAdmin() == true).collect(Collectors.toList()).get(0);
        // 我创建任务的数据
        List<FsUserAppTable> myCreateTask = userAppTables.stream().filter(fsUserAppTable -> fsUserAppTable.getIsAdmin() == false && fsUserAppTable.getIsBuildTask() == true).collect(Collectors.toList());
        // 我接收的任务
        List<FsUserAppTable> myRecTask = userAppTables.stream().filter(fsUserAppTable -> fsUserAppTable.getIsAdmin() == false && fsUserAppTable.getIsBuildTask() == false).collect(Collectors.toList());

        FsTask fsTask;
        FsTask fsTaskDb;
        for (FsUserAppTable userAppTable : myCreateTask) {
            // 拉去数据
            JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, userAppTable.getAppToken(), userAppTable.getTableId()), null);
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
                            List<FsTask.Person> issuer = fields.getJSONArray("发布人").toJavaList(FsTask.Person.class);
                            fsTask.setIssuer(issuer);
                        }
                        // 执行人
                        if (fields.getJSONArray("执行人") != null) {
                            List<FsTask.Person> executor = fields.getJSONArray("执行人").toJavaList(FsTask.Person.class);
                            fsTask.setExecutor(executor);
                        }
                        fsTaskDb = fsTaskMapper.selectById(fsTask.getTaskId());
                        if (fsTaskDb == null) {
                            // 需要插入数据库
                            fsTask.setCreateTime(new Date());
                            fsTask.setTaskClassify(0);
                            fsTaskMapper.insert(fsTask);
                            // 需要发送消息
//                            if ("已发布".equals(fsTask.getTaskStatus())) {
//                                fsTasksWatingSendMsg.add(fsTask);
//                            }
                        } else {
                            // 需要更新
                            fsTask.setUpdateTime(new Date());
                            fsTaskMapper.updateById(fsTask);
                            // 需要发送消息
//                            if ("已发布".equals(fsTask.getTaskStatus()) && !"已发布".equals(fsTaskDb.getTaskStatus()))
//                                fsTasksWatingSendMsg.add(fsTask);
                        }
                        // 插入到总表
                        fsTask.setTaskId(fsTask.getId());
                        // rowId
                        fsTask.setId(writeRecord(totalTable.getAppToken(), totalTable.getTableId(), itemObject));
                        fsTask.setTaskClassify(3);
                        fsTask.setCreateTime(new Date());
                        fsTaskMapper.insert(fsTask);
                    }
                }
            } else {
                log.error(res.toJSONString());
                throw new RuntimeException(res.getString("msg"));
            }
        }
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

}
