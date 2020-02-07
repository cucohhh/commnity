package com.life.demo.Controller;

import com.life.demo.Dto.QuestionDTO;
import com.life.demo.Model.Question;
import com.life.demo.Model.User;
import com.life.demo.mapper.QuestionMapper;
import com.life.demo.mapper.UserMapper;
import com.life.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable (name = "id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("tittle",question.getTittle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "tittle" ,required = false) String tittle,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false)String tag,
            @RequestParam(value = "id" ,required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("tittle",tittle);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);


        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            System.out.println("用户未登陆");
            model.addAttribute("error","用户未登陆");
            return "publish";
        }



        Question question = new Question();
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setTittle(tittle);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCommentCount(0);
        question.setLikeCount(0);
        question.setViewCount(0);
        question.setId(id);

        questionService.createOrUpdate(question);
       // questionMapper.create(question);


        return "redirect:/";
    }
}
