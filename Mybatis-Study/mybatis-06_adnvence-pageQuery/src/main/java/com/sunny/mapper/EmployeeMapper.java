package com.sunny.mapper;

/*
    高级查询 + 分页查询
    1: 查询结果总数
    2: 查询结果集
 */

import com.sunny.domain.Employee;
import com.sunny.query.EmployeeQueryObject;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 根据查询条件来查询员工
     * @param qo 封装查询条件的类对象
     * @return
     */
    List<Employee> queryForList(EmployeeQueryObject qo);

    /**
     * 根据查询条件来查询员工人数
     * @param qo 封装查询条件的类对象
     * @return
     */
    int queryForEmpCount(EmployeeQueryObject qo);
}
