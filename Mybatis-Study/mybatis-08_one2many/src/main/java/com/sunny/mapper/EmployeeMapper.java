package com.sunny.mapper;


import com.sunny.domain.Employee;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 保存一个员工
     */
    void save(Employee emp);


    List<Employee> getUsersByDeptId(Long deptId);
}
