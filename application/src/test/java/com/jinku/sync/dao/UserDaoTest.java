package com.jinku.sync.dao;

import com.google.common.collect.Lists;
import com.jinku.sync.BaseTest;
import org.jinku.sync.repository.dao.UserDao;
import org.jinku.sync.repository.dao.po.UserPo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback
    public void save() {
        UserPo user = new UserPo();
        user.setUserId("111");
        user.setUserName("测试1");
        user.setNickName("昵称11");
        user.setEmail("123@163.com");
        int result = userDao.save(user);
        Assert.assertEquals(result, 1);

        List<UserPo> users = userDao.getByUserIds(Lists.newArrayList("111"));
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void getByUserIds() {
        List<UserPo> users = userDao.getByUserIds(Lists.newArrayList("123"));
        Assert.assertTrue(users.size() >= 1);
    }
}
