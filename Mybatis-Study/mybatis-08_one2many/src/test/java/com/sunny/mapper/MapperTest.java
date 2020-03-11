package com.sunny.mapper;


import com.sunny.domain.Department;
import com.sunny.domain.Employee;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;



public class MapperTest {

    /**
     * 测试保存操作
     */
    @Test
    public void test() {
        // 创建部门对象
        Department dept = new Department();
        dept.setName("开发部");
        // 创建员工对象
        Employee emp = new Employee();
        emp.setName("张三");

        Employee emp2 = new Employee();
        emp2.setName("李四");

        // 维护对象关系(将员工添加到部门中)
        dept.getEmps().add(emp);
        dept.getEmps().add(emp2);

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        // 保存部门信息
        departmentMapper.save(dept);
        // 当保存部门信息后,再获取部门ID
        emp.setDeptId(dept.getId());
        emp2.setDeptId(dept.getId());
        // 保存员工信息
        employeeMapper.save(emp);
        employeeMapper.save(emp2);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 查询10号ID的部门信息(包括部门的员工)
     */
    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department dept = departmentMapper.getDeptById(10L);

        System.out.println(dept);
        System.out.println(dept.getEmps());

        /*
        * 手动完成 额外SQL查询
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Long deptId = dept.getId();
        List<Employee> emps = employeeMapper.getUsersByDeptId(deptId);
        dept.setEmps(emps);
        List<Employee> emps1 = dept.getEmps();
        System.out.println(emps1);
        */

        sqlSession.close();
    }

}
