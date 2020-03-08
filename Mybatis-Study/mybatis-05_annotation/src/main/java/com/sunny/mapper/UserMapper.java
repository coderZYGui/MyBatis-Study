package com.sunny.mapper;

import com.sunny.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 查询某个用户
    @Select("SELECT id AS u_id, name AS u_name, pwd AS u_pwd FROM user WHERE id = ${id}")
    @ResultMap("BaseResultMap")
    User getOneUser(int id);

    // 查询所有用户
    @Select("SELECT id AS u_id, name AS u_name, pwd AS u_pwd FROM user")
    @Results(id = "BaseResultMap", value = {
            @Result(column = "u_id", property = "id"),
            @Result(column = "u_name", property = "name"),
            @Result(column = "u_pwd", property = "pwd")
    })
    List<User> getUserList();


    // 根据id来更新用户信息
    @Update("UPDATE user SET name = #{name}, pwd = #{pwd} WHERE id = #{id}")
    int updateUser(User user);

    // 插入一条用户信息
    @Insert("INSERT INTO user (id, name, pwd) VALUES (null , #{name}, #{pwd})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    // 根据id来删除用户
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(int id);

}
