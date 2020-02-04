package com.life.demo.mapper;

import com.life.demo.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (tittle,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{tittle},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    public void create(Question question);
}
