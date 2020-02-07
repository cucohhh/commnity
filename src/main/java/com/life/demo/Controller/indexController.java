package com.life.demo.Controller;

import com.life.demo.Dto.PaginationDTO;
import com.life.demo.Dto.QuestionDTO;
import com.life.demo.Model.User;
import com.life.demo.mapper.UserMapper;
import com.life.demo.services.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.netty.http.Cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size

    ){



        //List<PaginationDTO> questionDTOList = questionService.List(page,size);
        PaginationDTO paginationDTO = questionService.List(page,size);


        model.addAttribute("paginationDTO",paginationDTO);

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "index";
        }

        return "index";
    }
}
