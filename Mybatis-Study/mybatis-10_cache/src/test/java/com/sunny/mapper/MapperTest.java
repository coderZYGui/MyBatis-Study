package com.sunny.mapper;

import com.sunny.domain.Teacher;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MapperTest {

    /**
     * 根据id来查询老师信息
     */
    @Test
    public void testOneCache(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher t1 = teacherMapper.getById(1L);
        System.out.println(t1);

        // 不同sqlSession中,缓存不能共享,在seesion中都会开启一个类似Map结构的缓存
        /*SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper1 = sqlSession1.getMapper(TeacherMapper.class);
        Teacher t2 = teacherMapper1.getById(1L);
        System.out.println(t2); // 默认开启了一级缓存*/

        // 清除一级缓存
//         sqlSession.clearCache();
        Teacher t2 = teacherMapper.getById(1L);
        System.out.println(t2);

        sqlSession.close();
    }

    @Test
    public void testTwoCache(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        teacherMapper.getById(1L);
        sqlSession.close();

        System.out.println("-----------------------");

        sqlSession = MybatisUtils.getSqlSession();
        teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        teacherMapper.getById(1L);
        sqlSession.close();
    }
}
