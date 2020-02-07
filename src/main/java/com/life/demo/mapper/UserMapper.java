package com.life.demo.mapper;

import com.life.demo.Model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void Insert(User user);

    @Select("Select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("Select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("Select * from user where account_id=#{accountId}")
    User findByaccountId(@Param(value = "accountId") String accountId);

    @Update("update user set name=#{name} ,token=#{token},gmt_modified=#{gmtModified},avatar_url = #{avatarUrl} where id= #{id}")
    void update(User dbuser);
}
