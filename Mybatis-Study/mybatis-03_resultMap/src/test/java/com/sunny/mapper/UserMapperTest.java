package com.sunny.mapper;

import com.sunny.domain.User;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserMapperTest {


    /**
     * 查询用户个数
     * @throws IOException
     */
    @Test
    public void testQueryUserCount(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.queryUserCount();
        System.out.println(count);
    }


    /**
     * 查询所有用户
     *
     * @throws IOException
     */
    @Test
    public void testQueryUserList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.queryUserList();
        for (User user : users) {
            System.out.println(user);
        }
        // 增删改需要提交事务, 查询不需要
        sqlSession.close();
    }
}
