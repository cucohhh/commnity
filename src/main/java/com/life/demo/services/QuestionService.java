package com.life.demo.services;

import com.life.demo.Dto.PaginationDTO;
import com.life.demo.Dto.QuestionDTO;
import com.life.demo.Model.Question;
import com.life.demo.Model.User;
import com.life.demo.mapper.QuestionMapper;
import com.life.demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO List(Integer page, Integer size) {

        Integer offset = size * (page -1);
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.count();

        List<Question> questionlist = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page =1;
        }
        if(page> paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }

        for (Question question : questionlist) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            if(user !=null){
                questionDTO.setUserAvatar(user.getAvatarUrl());
            }
           //
            questionDTOList.add(questionDTO);

        }


        paginationDTO.setQuestion(questionDTOList);



        return paginationDTO;
    }


    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page -1);
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.countByUserId(userId);

        List<Question> questionlist = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page =1;
        }
        if(page> paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }

        for (Question question : questionlist) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            if(user !=null){
                questionDTO.setUserAvatar(user.getAvatarUrl());
            }
            //
            questionDTOList.add(questionDTO);

        }


        paginationDTO.setQuestion(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {

        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUserAvatar(user.getAvatarUrl());
        questionDTO.setUser(user);
        return questionDTO;


    }
}
