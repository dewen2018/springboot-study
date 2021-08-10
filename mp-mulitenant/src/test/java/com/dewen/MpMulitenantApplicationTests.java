package com.dewen;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dewen.config.PresentContext;
import com.dewen.entity.User;
import com.dewen.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
class MpMulitenantApplicationTests {
    @Autowired
    private PresentContext apiContext;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 模拟当前系统的多租户Id
     */
    @Before
    public void before() {
        // 在上下文中设置当前多租户id
        apiContext.setCurrentTenantId(1L);
    }

    @Test
    @Transactional
    // 不会滚事务
    @Rollback(value = false)
    public void insert() {
        before();
        // 新增数据
        User user = new User();
        user.setName("小红");
        //判断一个条件是true还是false
        Assert.assertTrue(userMapper.insert(user) > 0);
        user = userMapper.selectById(user.getId());
        log.info("插入数据:{}", user);
        // 判断是否相等
        Assert.assertEquals(apiContext.getCurrentTenantId(), user.getTenantId());
    }

    @Test
    public void selectList() {
        userMapper.selectList(null).forEach((e) -> {
            log.info("查询数据{}", e);
            Assert.assertEquals(apiContext.getCurrentTenantId(), e.getTenantId());
        });
    }

    @Test
    public void select() {
        before();
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getId, 18));
        users.forEach(System.out::println);
    }

    @Test
    public void selectUserList() {
        before();
        List<User> users = userMapper.selectUserList2();
        users.forEach(System.out::println);
    }
}
