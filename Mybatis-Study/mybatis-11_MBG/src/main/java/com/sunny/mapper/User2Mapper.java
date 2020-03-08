package com.sunny.mapper;

import com.sunny.domain.User2;
import com.sunny.domain.User2Example;
import com.sunny.domain.User2Key;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User2Mapper {
    long countByExample(User2Example example);

    int deleteByExample(User2Example example);

    int deleteByPrimaryKey(User2Key key);

    int insert(User2 record);

    int insertSelective(User2 record);

    List<User2> selectByExample(User2Example example);

    User2 selectByPrimaryKey(User2Key key);

    int updateByExampleSelective(@Param("record") User2 record, @Param("example") User2Example example);

    int updateByExample(@Param("record") User2 record, @Param("example") User2Example example);

    int updateByPrimaryKeySelective(User2 record);

    int updateByPrimaryKey(User2 record);
}