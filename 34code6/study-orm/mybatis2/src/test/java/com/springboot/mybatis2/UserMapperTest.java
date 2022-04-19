package com.springboot.mybatis2;

import com.springboot.mybatis2.entity.UserEntity;
import com.springboot.mybatis2.enums.UserSexEnum;
import com.springboot.mybatis2.mapper.test1.User1Mapper;
import com.springboot.mybatis2.mapper.test2.User2Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private User1Mapper userMapper;
    @Autowired
    private User2Mapper userMapper2;

    @Test
    public void testInsert() throws Exception {
        userMapper2.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        userMapper2.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        userMapper2.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
        /**
         * 测试a是否等于b（a和b是原始类型数值(primitive value)或者必须为实现比较而具有equal方法）
         * assertEquals断言两个对象相等，若不满足，方法抛出带有相应信息
         */
        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> list = userMapper2.getAll();
        System.out.print(list);
    }

    @Test
    public void testUpdate() throws Exception {
        UserEntity userEntity = userMapper2.getOne(30L);
        System.out.print(userEntity.toString());
    }
}
