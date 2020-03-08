package com.sunny.mapper;

import com.sunny.domain.User;

import java.util.List;

public interface UserMapper {

    // 查询所有用户
    List<User> queryUserList();

    // 查询一共有多少用户
    int queryUserCount();

}
