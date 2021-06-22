package com.dewen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.consts.ReqConst;
import com.dewen.entity.FsDepartment;
import com.dewen.mapper.FsDepartmentMapper;
import com.dewen.service.IFsDepartmentService;
import com.dewen.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Service
public class FsDepartmentServiceImpl extends ServiceImpl<FsDepartmentMapper, FsDepartment> implements IFsDepartmentService {

    @Resource
    private FsDepartmentMapper fsDepartmentMapper;

    @Override
    @Transactional
    public void synFsDepartment() {
        List<FsDepartment> fsDepartments = new ArrayList<>();
        // 顶层
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("parent_department_id", 0);
        JSONObject res = HttpUtil.get(ReqConst.DEPARTMENTS_LIST, paramsMap);
        if (0 == res.getInteger("code") && "success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            List<FsDepartment> _childDep = JSONObject.parseArray(_obj.getString("items"), FsDepartment.class);
            fsDepartments.addAll(_childDep);
            // 递归查询
            recSearchDepartment(fsDepartments, _childDep);
            // 删除后重新添加
            this.baseMapper.delete(new QueryWrapper<>());
            if (fsDepartments.size() > 0) {
                fsDepartmentMapper.insertFsDepartments(fsDepartments);
            }
        } else {
            log.error(res.toJSONString());
        }
    }

    private List<FsDepartment> recSearchDepartment(List<FsDepartment> fsDepartments, List<FsDepartment> childDep) {
        for (FsDepartment fsDepartment : childDep) {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("parent_department_id", fsDepartment.getOpenDepartmentId());
            JSONObject res = HttpUtil.get(ReqConst.DEPARTMENTS_LIST, paramsMap);
            if (0 == res.getInteger("code") && "success".equals(res.get("msg"))) {
                JSONObject _obj = res.getJSONObject("data");
                List<FsDepartment> _childDep = JSONObject.parseArray(_obj.getString("items"), FsDepartment.class);
                if (_childDep != null) {
                    fsDepartments.addAll(_childDep);
                    recSearchDepartment(fsDepartments, _childDep);
                }
            }
        }
        return fsDepartments;
    }
}
