package com.sunny.mapper;


import com.sunny.domain.Student;
import com.sunny.domain.Teacher;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MapperTest {

    /**
     * 根据Id查询学生信息(包括该学生的老师们)
     */
    @Test
    public void testQueryByStuId(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.get(1L);
        System.out.println(student);
        System.out.println(student.getTeachers());
    }

    /**
     * 将学生、老师信息存储到各自的表中
     *  并将学生、老师的ID插入到中间表中
     */
    @Test
    public void testSave() {
        Teacher t1 = new Teacher();
        t1.setName("老师1");
        Teacher t2 = new Teacher();
        t2.setName("老师2");

        Student s1 = new Student();
        s1.setName("张三");
        Student s2 = new Student();
        s2.setName("李四");

        // 维护关系(学生的老师们)
        s1.getTeachers().add(t1);
        s1.getTeachers().add(t2);
        s2.getTeachers().add(t1);
        s2.getTeachers().add(t2);

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        studentMapper.save(s1);
        studentMapper.save(s2);
        teacherMapper.save(t1);
        teacherMapper.save(t2);

        // 当学生和老师信息都保存之后,再维护中间表的数据
        for (Teacher teacher1 : s1.getTeachers()) {
            studentMapper.insertRelationWithTeacher(s1.getId(), teacher1.getId());
        }
        for (Teacher teacher2 : s2.getTeachers()) {
            studentMapper.insertRelationWithTeacher(s2.getId(), teacher2.getId());
        }

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据ID删除
     */
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        // 因为多对多关系, 中间表中列,是两表中的主键,当删除一个学生时,中间表也必然发生改变
        // 在删除学生之前,应该先删除中间表中的数据
        studentMapper.deleteRelationTeacherById(1L);

        studentMapper.deleteStuById(1L);

        sqlSession.commit();
        sqlSession.close();
    }

}
