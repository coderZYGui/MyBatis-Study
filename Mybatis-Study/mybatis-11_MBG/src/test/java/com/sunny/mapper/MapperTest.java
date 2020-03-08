package com.sunny.mapper;

import com.sunny.domain.User2;
import com.sunny.domain.User2Key;
import com.sunny.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


public class MapperTest {

    /**
     * 测试MBG的查询
     */
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        User2Mapper user2Mapper = sqlSession.getMapper(User2Mapper.class);

        User2Key user2Key = new User2Key();
        user2Key.setId(1L);
        User2 user2 = user2Mapper.selectByPrimaryKey(user2Key);

        sqlSession.close();
    }

}
