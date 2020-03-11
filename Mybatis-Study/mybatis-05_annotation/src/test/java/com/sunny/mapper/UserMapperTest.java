package com.sunny.mapper;

import com.sunny.domain.User;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

    /**
     * 查询所有用户
     *
     * @throws IOException
     */
    @Test
    public void testQueryUserList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUserList();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }


    /**
     * 修改一条语句的内容
     *
     * @throws Exception
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(4);
        user.setName("土雅");
        user.setPwd("10004");
        // 获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int update = mapper.updateUser(user);

        // 方式二(以前的,不推荐写)
        // int update = sqlSession.update("com.sunny.dao.UserMapper.update",user);

        if (update > 0) {
            System.out.println("成功修改了:" + update + " 条语句!");
        }
        // 增删改必须提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }


    /**
     * 插入一条数据
     * @throws Exception
     */
    @Test
    public void testInsertUser(){
        // 获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User u = new User("coder", "10007");
        int count = mapper.insertUser(u);
        if (count > 0){
            System.out.println("插入成功!");
        }
        // 提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();

        System.out.println(u);
    }


    /**
     * 根据id来删除用户
     * @throws Exception
     */
    @Test
    public void testDeleteUser() throws Exception{
        // 加载mybatis全局配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 构建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 根据工厂对象来创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 根据sqlSession对象来获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 通过mapper来调用接口方法
        int count = mapper.deleteUser(5);
        if (count > 0){
            System.out.println("删除成功");
        }
        // 提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }
}
