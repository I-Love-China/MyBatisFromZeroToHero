package com.aliyun.code.typist;

import com.aliyun.code.typist.mapper.UserMapper;
import com.aliyun.code.typist.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SysUserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            //
            // SysUser sysUser = sqlSession.selectOne("com.aliyun.code.typist.mapper.UserMapper.selectById", 1L);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectById(1L);
            System.out.println(sysUser);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();
            user.setUserName("ZhangSan");
            user.setUserInfo("测试插入新用户");
            user.setUserEmail("123@qq.com");
            user.setCreateTime(new Date());

            int rows = userMapper.insert(user);
            Assert.assertEquals(1, rows);
            Assert.assertNotNull(user.getId());

            sqlSession.rollback();
        }
    }

    @Test
    public void testUpdateById() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1L);
            Assert.assertNotNull(user);

            user.setUserInfo("测试 update");
            int rows = userMapper.updateById(user);

            Assert.assertEquals(rows, 1);

            sqlSession.rollback();
        }
    }

    @Test
    public void testDeleteById() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1L);
            Assert.assertNotNull(user);

            int rows = userMapper.deleteById(user);
            Assert.assertEquals(rows, 1);

            int rows_2 = userMapper.deleteById(1001L);
            Assert.assertEquals(rows_2, 1);

            sqlSession.rollback();
        }
    }

    @Test
    public void testSelectByUserAndPwd() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            Map<String, String> params = new HashMap<>();
            params.put("userName", "admin");
            params.put("userPassword", "123456");

            List<SysUser> userList = userMapper.selectByUserAndPwd(params);
            Assert.assertNotNull(userList);
            Assert.assertTrue(!userList.isEmpty());

            List<SysUser> userList_2 = userMapper.selectByUserAndPwd("test", "123456");
            Assert.assertNotNull(userList_2);
            Assert.assertTrue(!userList_2.isEmpty());
        }
    }

    @Test
    public void testSelectByUser() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser condition = new SysUser();
            condition.setUserName("admin");
            condition.setUserPassword("123456");

            List<SysUser> users = userMapper.selectByUser(condition);
            Assert.assertNotNull(users);
            Assert.assertTrue(!users.isEmpty());
        }
    }


    @Test
    public void testSelectByIdOrUserName() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser condition = new SysUser();
            condition.setUserName("admin");

            List<SysUser> users = userMapper.selectByIdOrUserName(condition);
            Assert.assertNotNull(users);
            Assert.assertTrue(!users.isEmpty());

            SysUser cond = new SysUser();
            cond.setId(1001L);

            List<SysUser> users_1 = userMapper.selectByIdOrUserName(cond);
            Assert.assertNotNull(users_1);
            Assert.assertTrue(!users_1.isEmpty());
        }
    }

    @Test
    public void testSelectByWhere() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysUser condition = new SysUser();
            condition.setUserName("admin");
            condition.setUserPassword("123456");

            List<SysUser> users = sqlSession.getMapper(UserMapper.class).selectByWhere(condition);
            Assert.assertNotNull(users);
            Assert.assertTrue(!users.isEmpty());
        }
    }

    @Test
    public void testSelectByTrim_Where() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysUser condition = new SysUser();
            condition.setUserName("admin");
            condition.setUserPassword("123456");

            List<SysUser> users = sqlSession.getMapper(UserMapper.class).selectByTrim_Where(condition);
            Assert.assertNotNull(users);
            Assert.assertTrue(!users.isEmpty());
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1L);
            Assert.assertNotNull(user);

            user.setUserEmail("测试 updateByIdSelective");
            int rows = userMapper.updateByIdSelective(user);
            Assert.assertEquals(rows, 1);

            sqlSession.rollback();
        }
    }

    @Test
    public void testUpdateByTrim_Set() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1L);
            Assert.assertNotNull(user);

            user.setUserEmail("测试 updateByIdSelective");
            int rows = userMapper.updateByTrim_Set(user);
            Assert.assertEquals(rows, 1);

            sqlSession.rollback();
        }
    }

    @Test
    public void testSelectByIdList() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<Long> ids = Arrays.asList(1L, 1001L);
            List<SysUser> users = userMapper.selectByIdList(ids);

            Assert.assertNotNull(users);
            Assert.assertEquals(users.size(), 2);
        }
    }

    @Test
    public void testSelectByIdArray() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            Long[] ids = new Long[]{1L, 1001L};
            List<SysUser> users = userMapper.selectByIdArray(ids);

            Assert.assertNotNull(users);
            Assert.assertEquals(users.size(), 2);
        }
    }

    @Test
    public void testUpdateByMap() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Object> param = new HashMap<>();
            param.put("id", 1L);
            param.put("user_info", "测试 UpdateByMap");

            int rows = userMapper.updateByMap(param);
            Assert.assertEquals(rows, 1);

            sqlSession.rollback();
        }
    }
}
