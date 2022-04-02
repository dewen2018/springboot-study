package com.dewen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.entity.FsDepartment;
import com.dewen.ldap.DaoSupport;
import com.dewen.mapper.FsDepartmentMapper;
import com.dewen.pfOmUser.User;
import com.dewen.service.IFsDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
@Slf4j
public class FsDepartmentServiceImpl extends ServiceImpl<FsDepartmentMapper, FsDepartment> implements IFsDepartmentService {

    @Resource
    private FsDepartmentMapper fsDepartmentMapper;

    @Autowired
    private DaoSupport daoSupport;

    @Override
    public void pageTest() {
//         saleOrderCode like '%balabal%' and
        String sql = "select * from sale_order where ssoDepId in (select oxPermOrgId from ox_user_org where userId = 1621397715185)";
//        new Page<T>
        IPage<FsDepartment> res = fsDepartmentMapper.pageTest(new Page<FsDepartment>(1, 15), sql);
        res.getRecords().forEach(i -> {
            log.info(String.valueOf(i));
        });
    }

    @Override
    public void selectpage() throws ParseException {

        IPage<Map<String, Object>> res = fsDepartmentMapper.selectpage(new Page<FsDepartment>(1, 15));
        for (Map<String, Object> record : res.getRecords()) {
            System.out.println(record);

            getDate(record.get("lastUpdateTime"));
        }
//        IPage<JSONObject> res = fsDepartmentMapper.selectpage(new Page<FsDepartment>(1, 15));
//        for (JSONObject record : res.getRecords()) {
//            System.out.println(record);
//            System.out.println(record.getDate("lastUpdateTime"));
//        }
    }

    public void getDate(Object tmpDateExecuted) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateExecuted = null;
        if (tmpDateExecuted instanceof Date) {
            dateExecuted = (Date) tmpDateExecuted;
            System.out.println(df.format(dateExecuted));
        } else if (tmpDateExecuted instanceof LocalDateTime) {

        } else {

            try {
                dateExecuted = df.parse((String) tmpDateExecuted);
                System.out.println(dateExecuted);
            } catch (ParseException var24) {
            }
        }
    }


    public void getTest() {
        String sql = "select id, orgId, ssoOrgId, ssoUid, code, name, bizId, userType, orgName, status, mobile, post, parentId, account, password, salt, pwdUpdateTime, deleted," +
                "         headpic, createTime, createUserId, lastUpdateTime, lastUpdateUserId, remark, email, ssoDepId from pf_om_user where id = 1621397716076";
        log.info("sql:{}", sql);
        System.out.println(daoSupport.getBySql(User.class, sql));
    }
}
