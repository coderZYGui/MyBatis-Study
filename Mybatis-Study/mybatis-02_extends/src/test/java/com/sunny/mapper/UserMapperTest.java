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
import java.util.Map;

public class UserMapperTest {

    /**
     * 只查询User表中的id和name列
     * @throws Exception
     */
    @Test
    public void testQueryUserByIdAndName(){
        /*
            这种方式来查询User表中的部分列,其他列会显示null,可以将
         */
        /*SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.queryUserByIdAndName();
        for (User user : users) {
            System.out.println(user);
        }*/

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Map<Integer, Object>> maps = mapper.queryUserByIdAndName2();
        for (Map<Integer, Object> map : maps) {
            System.out.println(map);
        }
    }

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
//         List<User> userList = sqlSession.selectList("com.sunny.dao.UserMapper.getUserList");

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
    public void testQueryOneUser() throws IOException {
        // 加载全局配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 构建sqlSessionFactory工厂类对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 根据sqlSession类对象来创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // sqlSession相当于Connection,来执行SQL语句
        //User user = sqlSession.selectOne("com.sunny.dao.UserMapper.getUser", 1L);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
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
