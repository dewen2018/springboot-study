package com.dewen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.FsDepartment;
import com.dewen.entity.FsUser;
import com.dewen.entity.FsUserDepartment;
import com.dewen.mapper.FsDepartmentMapper;
import com.dewen.mapper.FsUserDepartmentMapper;
import com.dewen.mapper.FsUserMapper;
import com.dewen.service.IFsUserService;
import com.dewen.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Service
public class FsUserServiceImpl extends ServiceImpl<FsUserMapper, FsUser> implements IFsUserService {

    @Resource
    private FsUserMapper fsUserMapper;
    @Resource
    private FsDepartmentMapper fsDepartmentMapper;
    @Resource
    private FsUserDepartmentMapper fsUserDepartmentMapper;

    @Override
    @Transactional
    public void synFsUser() {
        List<FsDepartment> fsDepartments = fsDepartmentMapper.selectALl();
        List<FsUser> fsUsers = new ArrayList<>();
        List<FsUserDepartment> fsUserDepartments = new ArrayList<>();

        for (FsDepartment fsDepartment : fsDepartments) {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("department_id", fsDepartment.getOpenDepartmentId());
            JSONObject res = HttpUtil.get(ReqConst.USER_LIST, paramsMap);
            if (0 == res.getInteger("code") && "success".equals(res.get("msg"))) {
                JSONObject _obj = res.getJSONObject("data");
                List<FsUser> _childDep = JSONObject.parseArray(_obj.getString("items"), FsUser.class);

                if (_childDep != null) {
                    fsUsers.addAll(_childDep);
                    for (FsUser fsUser : _childDep) {
                        List<String> deptIds = fsUser.getDepartmentIds();
                        for (String deptId : deptIds) {
                            fsUserDepartments.add(new FsUserDepartment(fsUser.getUserId(), deptId));
                        }
                    }
                }
            } else {
                log.error(res.toJSONString());
            }
        }
        // 删除后重新添加
        this.baseMapper.delete(new QueryWrapper<>());
        fsUserDepartmentMapper.delete(new QueryWrapper<>());
        //使用TreeSet去重
        fsUserDepartments = fsUserDepartments.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<FsUserDepartment>(Comparator.comparing(o -> o.getUserId() + "_" + o.getOpenDepartmentId()))), ArrayList::new));
        //使用map去重
        fsUsers = fsUsers.stream().filter(distinctByKey(o -> o.getUserId())).collect(Collectors.toList());
        // 批量插入
        fsUserMapper.insertFsUsers(fsUsers);
        fsUserDepartmentMapper.insertFsUserDepartments(fsUserDepartments);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
