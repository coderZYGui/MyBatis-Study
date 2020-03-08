package com.sunny.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// sqlSessionFactory 生产 sqlSession
public class MybatisUtils {

    // MyBatis文档中描述: SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，
    // 没有任何理由丢弃它或重新创建另一个实例。
    private static  SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 使用Mybatis的第一步: 获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
    // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }


    /*public static <T> T getMapper(Class<T> mapperClass){
        return getSqlSession().getMapper(mapperClass);
    }*/
}
