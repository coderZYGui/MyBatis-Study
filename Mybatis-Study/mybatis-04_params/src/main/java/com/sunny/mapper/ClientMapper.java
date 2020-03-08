package com.sunny.mapper;

import com.sunny.domain.Client;
import com.sunny.domain.LoginVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClientMapper {

    List<Client> queryAllUser();

    // 方式一: 把多个参数封装成JavaBean
    Client login1(LoginVO vo);

    // 方式二: 使用Map对象封装多个参数
    Client login2(Map<String, Object> paramMap);

    // 方式三: 使用Param注解, 原理是方式二. Param注解中的字符串就是Map中的key
    Client login3(@Param("username") String username, @Param("password") String password);

}
