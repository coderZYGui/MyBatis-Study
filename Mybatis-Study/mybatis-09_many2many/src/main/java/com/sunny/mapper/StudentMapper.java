package com.sunny.mapper;


import com.sunny.domain.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

    // 根据id来查询学生信息
    Student get(Long l);

    // 保存操作
    void save(Student stu);

    // 因为关系在学生这边.(因为传递的是两个参数,所以要做参数处理,使用注解)
    void insertRelationWithTeacher(@Param("studentId") Long studentId, @Param("teacherId") Long teacherId);

    // 根据id删除学生记录
    void deleteStuById(long l);

    // 当删除学生的id时,中间表中学生的id也应该删除
    void deleteRelationTeacherById(long l);

}
