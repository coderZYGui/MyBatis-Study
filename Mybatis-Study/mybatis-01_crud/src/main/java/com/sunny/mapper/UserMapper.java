package com.sunny.mapper;

import com.sunny.domain.User;

import java.util.List;

public interface UserMapper {
    // 查询所有用户
    List<User> getUserList();

    // 根据id来查询用户
    User getUser(int id);

    // 根据id来更新用户信息
    int updateUser(User user);

    // 插入一条用户信息
    int insertUser(User user);

    // 根据id来删除用户
    int deleteUser(int id);
}
