package com.sunny.mapper;


import com.sunny.domain.Employee;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapperTest {


    /**
     * 查询工资大于等于1000的员工
     */
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal("1001");
        List<Employee> employees = mapper.query1(minSalary);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        sqlSession.close();
    }

    /**
     * 查询工资在1000-2000之间的员工
     */
    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal("1000");
        BigDecimal maxSalary = new BigDecimal("2000");
        List<Employee> employees = mapper.query2(minSalary, maxSalary);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        sqlSession.close();
    }

    /**
     * 查询指定部门的员工信息
     */
    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal("100");
        BigDecimal maxSalary = new BigDecimal("1000");
        List<Employee> employees = mapper.query3(minSalary, maxSalary, 20L);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        sqlSession.close();
    }

    /**
     * 更新指定id的员工信息
     */
    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = new Employee();
        employee.setId(3L);
        employee.setSn("070708");
        employee.setName("Lucy");
        employee.setSalary(new BigDecimal("188"));

        int update = mapper.update(employee);
        if (update > 0){
            System.out.println("成功修改"+update+"条用户信息!");
        }

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 批量删除指定id的员工信息
     */
    @Test
    public void test5(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.batchDelete(new Long[]{10L,20L,30L});

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 批量插入员工信息
     */
    @Test
    public void test6(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee(null, "周", "10001", new BigDecimal("5555.00"), 50L));
        list.add(new Employee(null, "吴", "10002", new BigDecimal("6666.00"), 60L));
        list.add(new Employee(null, "郑", "10003", new BigDecimal("7777.00"), 70L));
        int count = mapper.batchInsert(list);
        if (count > 0){
            System.out.println("成功插入了:"+count+"条用户信息!");
        }

        sqlSession.commit();
        sqlSession.close();
    }
}
