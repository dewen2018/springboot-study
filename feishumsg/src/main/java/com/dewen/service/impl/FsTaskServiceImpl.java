package com.dewen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.Content;
import com.dewen.entity.FsTask;
import com.dewen.entity.FsTaskConfig;
import com.dewen.mapper.FsTaskConfigMapper;
import com.dewen.mapper.FsTaskMapper;
import com.dewen.service.IFsTaskService;
import com.dewen.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Service
public class FsTaskServiceImpl extends ServiceImpl<FsTaskMapper, FsTask> implements IFsTaskService {
    @Resource
    private FsTaskMapper fsTaskMapper;

    @Resource
    private FsTaskConfigMapper fsTaskConfigMapper;

    /**
     * 同步任务
     */
    @Override
    @Transactional
    public void synFsTask() {
        List<FsTask> fsTasksAdd = new ArrayList<>();
        List<FsTask> fsTasksUpdate = new ArrayList<>();
        List<FsTask> fsTasksWatingSendMsg = new ArrayList<>();

        FsTask fsTask;
        FsTask fsTaskDb;

        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, ReqConst.MULT_TABLE_TASK_APP_TOKEN, ReqConst.MULT_TABLE_TASK_TABLE_ID), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                for (Object item : jsonArray) {
                    fsTask = new FsTask();
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    // rowId
                    fsTask.setTaskId(itemObject.getString("id"));
                    JSONObject fields = itemObject.getJSONObject("fields");
                    // 任务名称
                    String taskName = fields.getString("任务名称");
                    if (StrUtil.isNotEmpty(taskName))
                        fsTask.setTaskName(taskName);

                    // 任务类型
                    JSONArray taskTypeObj = fields.getJSONArray("任务类型");
                    if (taskTypeObj != null) {
                        String taskType = JSONObject.parseObject(taskTypeObj.get(0).toString()).getString("text");
                        if (StrUtil.isNotEmpty(taskType))
                            fsTask.setTaskType(taskType);
                    }

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
                    fsTaskDb = this.baseMapper.selectById(fsTask.getTaskId());
                    if (fsTaskDb == null) {
                        // 需要插入数据库
                        fsTask.setCreateTime(new Date());
                        fsTasksAdd.add(fsTask);
                        // 需要发送消息
                        if ("已发布".equals(fsTask.getTaskStatus())) {
                            fsTasksWatingSendMsg.add(fsTask);
                        }
                    } else {
                        // 需要更新
                        fsTask.setUpdateTime(new Date());
                        // fsTasksUpdate.add(fsTask);
                        this.baseMapper.updateById(fsTask);
                        // 需要发送消息
                        if ("已发布".equals(fsTask.getTaskStatus()) && !"已发布".equals(fsTaskDb.getTaskStatus()))
                            fsTasksWatingSendMsg.add(fsTask);
                    }
                }
                // 插入操作
                if (fsTasksAdd.size() > 0)
                    fsTaskMapper.insertFsTasks(fsTasksAdd);
                // 更新操作
//                if (fsTasksUpdate.size() > 0)
//                    fsTaskMapper.updateFsTasks(fsTasksUpdate);
                // 发送消息
                if (fsTasksWatingSendMsg.size() > 0)
                    this.sendMsg(fsTasksWatingSendMsg);


            }
        } else {
            log.error(res.toJSONString());
        }
    }

    /**
     * 发送消息
     */
    public void sendMsg(List<FsTask> fsTasks) {
        QueryWrapper<FsTaskConfig> fsTaskConfigQueryWrapper = new QueryWrapper<>();
        fsTaskConfigQueryWrapper.lambda().in(FsTaskConfig::getUserGroup, fsTasks.stream().map(FsTask::getTaskType).collect(Collectors.toList()));
        List<FsTaskConfig> fsTaskConfigs = fsTaskConfigMapper.selectList(fsTaskConfigQueryWrapper);
        List<String> openIds;

        JSONObject msg = new JSONObject();
        msg.put("msg_type", "text");
        msg.put("content", new Content("任务已发布，请及时处理22！"));

        for (FsTask fsTask : fsTasks) {
            openIds = fsTaskConfigs.stream().filter(fsTaskConfig -> fsTaskConfig.getUserGroup() == null ? false
                    : (fsTaskConfig.getUserGroup().equals(fsTask.getTaskType()))).map(FsTaskConfig::getUserOpenId).collect(Collectors.toList());

            if (openIds.size() > 0) {
                msg.put("open_ids", openIds);
                JSONObject res = HttpUtil.post2(ReqConst.BATCH_SEND_MSG, null, msg);
                if (0 == res.getInteger("code") && "ok".equals(res.get("msg"))) {
                    JSONObject _obj = res.getJSONObject("data");
                    System.out.println(_obj);
                } else {
                    log.error(res.toJSONString());
                }
            }
        }
    }
}
