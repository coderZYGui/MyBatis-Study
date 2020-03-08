package com.sunny.mapper;


import com.sunny.domain.Employee;
import com.sunny.query.EmployeeQueryObject;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeMapperTest {

    /**
     * 按照员工的关键字、工资范围、所属部门来查询
     */
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setKeyword("2");
        qo.setMinSalary(new BigDecimal("1000"));
        qo.setMaxSalary(new BigDecimal("9000"));
        qo.setDeptId(30L);
        List<Employee> employees = mapper.queryForList(qo);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        sqlSession.close();
    }

    /**
     * 按照查询条件了查询员工的人数
     */
    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setKeyword("2");
        qo.setMinSalary(new BigDecimal("1000"));
        qo.setMaxSalary(new BigDecimal("9000"));
        qo.setDeptId(30L);
        int i = mapper.queryForEmpCount(qo);
        if (i > 0) {
            System.out.println("符合条件的一共有:"+i+"人!");
        }
        sqlSession.close();
    }
}
