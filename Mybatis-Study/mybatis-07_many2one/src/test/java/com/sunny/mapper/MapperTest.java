package com.sunny.mapper;


import com.sunny.domain.Department;
import com.sunny.domain.Employee;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

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
        emp.setDept(dept);  // 将张三分配到开发部中
        Employee emp2 = new Employee();
        emp2.setName("李四");
        emp2.setDept(dept); // 将李四分配到开发部中

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 保存操作:先保存部门(one方)因为,员工表中需要依赖于部门ID,先保存员工信息,部门信息可能为空
        departmentMapper.save(dept);
        employeeMapper.save(emp);
        employeeMapper.save(emp2);

        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 根据id来查询员工信息
     */
    @Test
    public void test1() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Employee employee = employeeMapper.getUserById(2L);

        // -------------------------------------
        /*
            因为获取的结果集中的列名和对象的属性名不匹配,设置resultMap解决不匹配问题,
               但是结果集中的id列,不能和对象中的dept对象匹配,只能和对象dept的属性id匹配
               所以,能获取到部门的id,但是期望得到的是Department对象
            解决方案: 根据部门的id,再 额外 发送一条SQL语句,查询出 部门对象 ,把部门对象设置给员工即可!
         */

        // 下面三行代码让,MyBatis完成,需要在resultMap中配置
        Long deptId = employee.getDept().getId();
        // 根据部门id来查询出,部门对象
        Department department = departmentMapper.getDeptById(deptId);
        employee.setDept(department);

        System.out.println(employee);
        System.out.println(employee.getDept());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> emps =  mapper.getAllUser();
        for (Employee emp : emps) {
            System.out.println(emp);
        }

        sqlSession.close();
    }
}
