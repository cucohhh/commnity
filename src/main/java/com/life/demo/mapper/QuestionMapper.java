package com.life.demo.mapper;

import com.life.demo.Dto.QuestionDTO;
import com.life.demo.Model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (tittle,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{tittle},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    public void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value="offset") Integer offset, @Param(value="size") Integer size);

    @Select("select count(1) from question")
    Integer count();
    @Select("select *from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(Integer id);

    @Update("update question set tittle=#{tittle} , description=#{description},tag=#{tag},gmt_Modified=#{gmtModified} where id =#{id}")
    void update(Question question);
}
