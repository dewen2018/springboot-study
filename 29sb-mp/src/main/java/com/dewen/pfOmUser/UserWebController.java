package com.dewen.pfOmUser;


import com.dewen.ldap.DaoSupport;
import com.dewen.ldap.jdbc.builder.QueryBuilder;
import com.dewen.ldap.lang.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * 部门信息 前端控制器
 *
 * @author dj
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UserWebController {
    @Resource
    private DaoSupport daoSupport;

    @GetMapping("/testMethod")
    public void pageTest() throws ParseException {
        String sql = "select count(*) from pf_om_user where id = 1621397716076";
        log.info("sql:{}", sql);
        log.info("result:{}", daoSupport.getBySql(Integer.class, sql));
    }

    @GetMapping("/testPage")
    public void testPage() throws ParseException {
        Paging<User> userPaging = daoSupport.paging(User.class, "pf_om_user", 0, 15, new String[]{"LENGTH(CODE)", "code + 0"}, new boolean[]{true, true});
        userPaging.getList().forEach(item -> {
            log.info("result:{}", item);
        });
    }

    @GetMapping("/testList")
    public void testList() throws ParseException {
        QueryBuilder queryBuilder = new QueryBuilder();
        List<User> users = daoSupport.list(User.class, "pf_om_user", queryBuilder, new String[]{"LENGTH(CODE)", "code + 0"}, new boolean[]{true, true});
        for (User item : users) {
            log.info("result:{}", item);
        }
    }
}
