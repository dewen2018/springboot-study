package com.dewen.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.FsTaskConfig;
import com.dewen.mapper.FsTaskConfigMapper;
import com.dewen.service.IFsTaskConfigService;
import com.dewen.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 任务配置 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Service
public class FsTaskConfigServiceImpl extends ServiceImpl<FsTaskConfigMapper, FsTaskConfig> implements IFsTaskConfigService {
    @Resource
    private FsTaskConfigMapper fsTaskConfigMapper;

    /**
     * 同步任务配置
     */
    @Override
    @Transactional
    public void synFsTaskConfig() {
        List<FsTaskConfig> fsTaskConfigs = new ArrayList<>();

        FsTaskConfig fsTaskConfig;
        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, ReqConst.MULT_TABLE_TASK_APP_TOKEN, ReqConst.MULT_TABLE_TASK_CONFIG_TABLE_ID), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                for (Object item : jsonArray) {
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    JSONObject fields = itemObject.getJSONObject("fields");
                    JSONArray userList = fields.getJSONArray("人员");
                    String userGroup = fields.getString("用户组");
                    if (userList != null) {
                        for (Object user : userList) {
                            JSONObject userObject = JSONObject.parseObject(user.toString());
                            fsTaskConfig = new FsTaskConfig();
                            if (userGroup != null)
                                fsTaskConfig.setUserGroup(userGroup);
                            fsTaskConfig.setUserName(userObject.getString("name"));
                            fsTaskConfig.setUserOpenId(userObject.getString("id"));
                            fsTaskConfigs.add(fsTaskConfig);
                        }
                    }
                }
                // 删除后重新添加
                this.baseMapper.delete(new QueryWrapper<>());
                // 批量插入
                if (fsTaskConfigs.size() > 0) {
                    fsTaskConfigMapper.insertFsTaskConfigs(fsTaskConfigs);
                }
            }
        } else {
            log.error(res.toJSONString());
        }
    }
}
