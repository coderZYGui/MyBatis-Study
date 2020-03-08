package com.sunny.mapper;


import com.sunny.domain.Department;

public interface DepartmentMapper {

    /**
     * 保存一个部门
     */
    void save(Department dept);

    /**
     * 根据部门ID来查询部门信息
     * @param l
     * @return
     */
    Department getDeptById(long l);
}
