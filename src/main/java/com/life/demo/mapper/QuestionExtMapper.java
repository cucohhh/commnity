package com.life.demo.mapper;

import com.life.demo.Model.Question;
import com.life.demo.Model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Wed Feb 12 21:03:56 CST 2020
     */

    int incView(Question record);
}