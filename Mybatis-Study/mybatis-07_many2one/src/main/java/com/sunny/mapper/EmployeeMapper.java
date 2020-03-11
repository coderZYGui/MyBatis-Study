package com.sunny.mapper;


import com.sunny.domain.Employee;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 保存一个员工
     */
    void save(Employee emp);

    /**
     * 根据传入的id来查询用户信息
     * @param l 用户id
     * @return
     */
    Employee getUserById(long empId);

    /**
     * 查询所有用户
     * @return
     */
    List<Employee> getAllUser();
}
