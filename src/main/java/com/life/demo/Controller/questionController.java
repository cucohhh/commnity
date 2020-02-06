package com.life.demo.Controller;

import com.life.demo.Dto.QuestionDTO;
import com.life.demo.mapper.QuestionMapper;
import com.life.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class questionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable (name="id") Integer id,
                           Model model){

        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}
