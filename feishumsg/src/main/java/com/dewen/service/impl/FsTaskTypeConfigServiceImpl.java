package com.dewen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.*;
import com.dewen.mapper.FsRoleUserConfigMapper;
import com.dewen.mapper.FsTaskTypeConfigMapper;
import com.dewen.mapper.FsTaskUserRelateMapper;
import com.dewen.mapper.FsUserAppTableMapper;
import com.dewen.service.IFsTaskTypeConfigService;
import com.dewen.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 飞书任务类型 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
@Service
public class FsTaskTypeConfigServiceImpl extends ServiceImpl<FsTaskTypeConfigMapper, FsTaskTypeConfig> implements IFsTaskTypeConfigService {
    @Resource
    private FsUserAppTableMapper fsUserAppTableMapper;
    @Resource
    private FsTaskTypeConfigMapper fsTaskTypeConfigMapper;
    @Resource
    private FsRoleUserConfigMapper fsRoleUserConfigMapper;
    @Resource
    private FsTaskUserRelateMapper fsTaskUserRelateMapper;

    @Override
    @Transactional
    public void synConfigInfo() {
        // 同步任务配置信息
        List<FsTaskTypeConfig> fsTaskTypeConfigs = new ArrayList<>();
        synTaskTypeConfig(fsTaskTypeConfigs);
        // 同步角色配置信息
        List<FsRoleUserConfig> fsRoleUserConfigs = new ArrayList<>();
        synRoleUserConfig(fsRoleUserConfigs);
        // 组装易用数据
        buildData(fsTaskTypeConfigs, fsRoleUserConfigs);


    }

    /**
     * 同步任务配置信息
     *
     * @return
     */
    @Transactional
    public void synTaskTypeConfig(List<FsTaskTypeConfig> fsTaskTypeConfigs) {
        // 删除 fs_task_type_config
        fsTaskTypeConfigMapper.delete(null);
        // 读取配置
        FsUserAppTable taskTypeConfig = fsUserAppTableMapper.selectOne(new QueryWrapper<FsUserAppTable>().lambda().eq(FsUserAppTable::getPurpose, "taskTypeConfig"));
        FsTaskTypeConfig fsTaskTypeConfig;
        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, taskTypeConfig.getSendAppToken(), taskTypeConfig.getSendTableId()), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                for (Object item : jsonArray) {
                    fsTaskTypeConfig = new FsTaskTypeConfig();
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    // rowId
                    fsTaskTypeConfig.setId(itemObject.getString("id"));
                    JSONObject fields = itemObject.getJSONObject("fields");
                    // 任务类型
                    String taskType = fields.getString("任务类型");
                    if (StrUtil.isNotEmpty(taskType))
                        fsTaskTypeConfig.setTaskType(taskType);
                    // 角色名称
                    if (fields.getJSONArray("角色名称") != null) {
                        itemObject = JSONObject.parseObject(fields.getJSONArray("角色名称").get(0).toString());
                        fsTaskTypeConfig.setRoles(itemObject.getString("text"));
                    }
                    fsTaskTypeConfigMapper.insert(fsTaskTypeConfig);
                    fsTaskTypeConfigs.add(fsTaskTypeConfig);
                }
            }
        } else {
            log.error(res.toJSONString());
            throw new RuntimeException(res.getString("msg"));
        }
    }

    /**
     * 同步角色配置信息
     *
     * @return
     */
    @Transactional
    public void synRoleUserConfig(List<FsRoleUserConfig> fsRoleUserConfigs) {
        // 删除 fs_role_user_config,
        fsRoleUserConfigMapper.delete(null);
        // 读取配置
        FsUserAppTable roleUserConfig = fsUserAppTableMapper.selectOne(new QueryWrapper<FsUserAppTable>().lambda().eq(FsUserAppTable::getPurpose, "roleUserConfig"));
        FsRoleUserConfig fsRoleUserConfig;
        JSONObject res = HttpUtil.get(String.format(ReqConst.MULI_TABLES_RECORD, roleUserConfig.getSendAppToken(), roleUserConfig.getSendTableId()), null);
        if (0 == res.getInteger("code") && "Success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            JSONArray jsonArray = JSONObject.parseArray(_obj.getString("items"));
            if (jsonArray != null) {
                for (Object item : jsonArray) {
                    fsRoleUserConfig = new FsRoleUserConfig();
                    JSONObject itemObject = JSONObject.parseObject(item.toString());
                    // rowId
                    fsRoleUserConfig.setId(itemObject.getString("id"));
                    JSONObject fields = itemObject.getJSONObject("fields");
                    // 任务类型
                    String roleName = fields.getString("角色名称");
                    if (StrUtil.isNotEmpty(roleName))
                        fsRoleUserConfig.setRoleName(roleName);
                    // 角色名称
                    if (fields.getJSONArray("人员") != null) {
                        List<FsTask.Person> executor = fields.getJSONArray("人员").toJavaList(FsTask.Person.class);
                        fsRoleUserConfig.setUsers(executor);
                    }
                    fsRoleUserConfigMapper.insert(fsRoleUserConfig);
                    fsRoleUserConfigs.add(fsRoleUserConfig);
                }
            }
        } else {
            log.error(res.toJSONString());
            throw new RuntimeException(res.getString("msg"));
        }
    }

    /**
     * 构建数据
     *
     * @param fsTaskTypeConfigs
     * @param fsRoleUserConfigs
     */
    @Transactional
    public void buildData(List<FsTaskTypeConfig> fsTaskTypeConfigs, List<FsRoleUserConfig> fsRoleUserConfigs) {
        //删除数据
        fsTaskUserRelateMapper.delete(null);
        FsTaskUserRelate fsTaskUserRelate;
        List<FsRoleUserConfig> configs;
        List<FsTask.Person> persons;
        // 遍历任务类型配置
        for (FsTaskTypeConfig fsTaskTypeConfig : fsTaskTypeConfigs) {
            if (StrUtil.isNotEmpty(fsTaskTypeConfig.getRoles())) {
                String[] roles = fsTaskTypeConfig.getRoles().split(",");
                // 遍历角色
                for (String role : roles) {
                    configs = fsRoleUserConfigs.stream().filter(s -> role.equals(s.getRoleName()) && s.getUsers() != null).collect(Collectors.toList());
                    // 极大可能只会有一个元素，但是这个还是采用了遍历
                    for (FsRoleUserConfig config : configs) {
                        persons = config.getUsers();
                        // 遍历人员
                        for (FsTask.Person person : persons) {
                            fsTaskUserRelate = new FsTaskUserRelate();
                            fsTaskUserRelate.setTaskType(fsTaskTypeConfig.getTaskType());
                            fsTaskUserRelate.setRoleName(role);
                            fsTaskUserRelate.setOpenId(person.getId());
                            fsTaskUserRelate.setName(person.getName());
                            fsTaskUserRelateMapper.insert(fsTaskUserRelate);
                        }
                    }
                }
            }
        }
    }
}
