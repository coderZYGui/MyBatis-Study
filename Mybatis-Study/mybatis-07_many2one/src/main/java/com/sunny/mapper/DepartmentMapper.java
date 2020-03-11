package com.sunny.mapper;

import com.sunny.domain.Department;

public interface DepartmentMapper {

    /**
     * 保存一个部门
     */
    void save(Department dept);

    /**
     * 根据部门的id查询出部门对象
     */
    Department getDeptById(Long deptId);
}
