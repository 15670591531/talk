package org.docryze.talk.baseprovider.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.docryze.talk.talkmodel.table.User;

/**
 * base provider下的UserMapper
 */
@Mapper
public interface BaseUserMapper {

    @Select("select * from user where id = #{id}")
    User selectUserById(@Param("id") String id);
}
