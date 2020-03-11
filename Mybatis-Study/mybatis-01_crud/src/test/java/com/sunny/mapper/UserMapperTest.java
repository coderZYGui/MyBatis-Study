package com.sunny.mapper;

import com.sunny.domain.User;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

    /**
     * 自定义日志操作
     */
    private static Logger logger = Logger.getLogger(UserMapperTest.class);

    @Test
    public void testLogger() throws Exception {
        // 如果日志输出级别为INFO,则输出
        if (logger.isInfoEnabled()) {
            logger.info("银行转账操作");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("查询数据库");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("连接数据库");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("执行SQL");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("转账");
        }
        if (logger.isInfoEnabled()) {
            logger.info("银行转账成功");
        }
    }


    /**
     * 查询所有用户
     *
     * @throws IOException
     */
    @Test
    public void testQueryUserList() throws IOException {

        //1. 获得sqlSession对象
        // SqlSession sqlSession = MybatisUtils.getSqlSession();

        //1. 从classpath路径去加载MyBatis全局配置文件:mybatis-config.xml
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2. 创建sqlSessionFactory对象,好比是DataSource
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3. 创建sqlSession对象,好比是Connection
        SqlSession sqlSession = factory.openSession();
        //4. 具体操作
        // 执行SQL(方式一)
        /*
         执行SQL: UserMapper.xml中的namespace:绑定一个对应的Dao/Mapper接口相当于UserMapper接口的实现类,
         这里用UserMapper.class,就是面向接口编程,相当于获取UserMapper的实现类,然后通过实现类来调用接口方法
        */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();

        // 方式二
        // List<User> userList = sqlSession.selectList("com.sunny.dao.UserDao.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 查询id为1的用户
     *
     * @throws IOException
     */
    @Test
    public void testQueryOneUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 使用方式一: 来找到SQL并执行
        //User user = sqlSession.selectOne("com.sunny.dao.UserMapper.getUser", 1L);

        // 使用方式二: 来找到SQL并执行
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 实际上底层调用的还是sqlSession的方法,注意:sqlSession调用CRUD方法只能传递一个参数
        User user = mapper.getUser(1);

        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 修改一条语句的内容
     *
     * @throws Exception
     */
    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(4);
        user.setName("土土土雅");
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
    public void testInsertUser() throws Exception {
        // 获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        /*
         执行SQL: UserMapper.xml中的namespace:绑定一个对应的Dao/Mapper接口相当于UserMapper接口的实现类,
         这里用UserMapper.class,就是面向接口编程,相当于获取UserMapper的实现类,然后通过实现类来调用接口方法
        */
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
