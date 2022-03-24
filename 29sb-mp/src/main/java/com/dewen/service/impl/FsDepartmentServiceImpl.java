package com.dewen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.entity.FsDepartment;
import com.dewen.mapper.FsDepartmentMapper;
import com.dewen.service.IFsDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Service
@Slf4j
public class FsDepartmentServiceImpl extends ServiceImpl<FsDepartmentMapper, FsDepartment> implements IFsDepartmentService {

    @Resource
    private FsDepartmentMapper fsDepartmentMapper;

    @Override
    public void pageTest() {
//         saleOrderCode like '%balabal%' and
        String sql = "select * from sale_order where ssoDepId in (select oxPermOrgId from ox_user_org where userId = 1621397715185)";
//        new Page<T>
        IPage<FsDepartment> res = fsDepartmentMapper.pageTest(new Page<FsDepartment>(1, 15), sql);
        res.getRecords().forEach(i->{
            log.info(String.valueOf(i));
        });
    }

}
