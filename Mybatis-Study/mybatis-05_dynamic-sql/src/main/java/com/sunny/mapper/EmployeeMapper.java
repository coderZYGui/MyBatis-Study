package com.sunny.mapper;

import com.sunny.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeMapper {

    /**
     * 查询工资大于1000的员工
     */
    List<Employee> query1(@Param("minSalary") BigDecimal minSalary);

    /**
     * 查询工资在1000-2000之间
     * @param minSalary
     * @param maxSalary
     * @return
     */
    List<Employee> query2(
            @Param("minSalary") BigDecimal minSalary,
            @Param("maxSalary") BigDecimal maxSalary
    );

    /**
     * 查询指定部门的员工信息
     * @param minSalary
     * @param maxSalary
     * @param deptId
     * @return
     */
    List<Employee> query3(
            @Param("minSalary") BigDecimal minSalary,
            @Param("maxSalary") BigDecimal maxSalary,
            @Param("deptId") Long deptId
    );

    /**
     * 使用set元素来动态更新SQL
     * @param employee
     * @return
     */
    int update(Employee employee);

    /**
     * 使用foreach元素批量删除
     * @param ids
     * param注解原理还是Map,Map的key
     */
    void batchDelete(@Param("ids") Long[] ids);

    /**
     * 批量插入用户信息
     * @param list
     * @return
     * 当参数是数组或集合时,一般要加上@Param注解,写死
     */
    int batchInsert(@Param("emps") List<Employee> emps);
}
