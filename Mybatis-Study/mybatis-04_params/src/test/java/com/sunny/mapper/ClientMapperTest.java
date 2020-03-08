package com.sunny.mapper;

import com.sunny.domain.Client;
import com.sunny.domain.LoginVO;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientMapperTest {

    /**
     * 方式一: 使用JavaBean来封装
     */
    @Test
    public void testLogin1(){
        LoginVO vo = new LoginVO("zy", "1111");

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ClientMapper mapper = sqlSession.getMapper(ClientMapper.class);
        // mapper调用方法,实际底层仍是sqlSession来调用select方法
        Client client = mapper.login1(vo);
        System.out.println(client);
        sqlSession.close();
    }

    /**
     * 使用Map来封装
     */
    @Test
    public void testLogin2(){

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", "zy");
        paramMap.put("password", "1111");

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ClientMapper mapper = sqlSession.getMapper(ClientMapper.class);
        // mapper调用方法,实际底层仍是sqlSession来调用select方法.
        // 注意: sqlSession调用的方法,只能传递一个参数.
        Client client = mapper.login2(paramMap);
        System.out.println(client);
        sqlSession.close();
    }

    /**
     * 方式三: 使用Param注解,原理是方式二
     */
    /*
        HelloWorld
     */
    @Test
    public void testLogin3(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ClientMapper mapper = sqlSession.getMapper(ClientMapper.class);
        // mapper调用方法,实际底层仍是sqlSession来调用select方法.
        // 注意: sqlSession调用的方法,只能传递一个参数.
        Client client = mapper.login3("zy", "1111");
        System.out.println(client);
        sqlSession.close();
    }




    /**
     * 测试
     */
    @Test
    public void testQueryAllUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ClientMapper mapper = sqlSession.getMapper(ClientMapper.class);
        List<Client> clients = mapper.queryAllUser();
        for (Client client : clients) {
            System.out.println(client);
        }

        sqlSession.close();
    }

}
