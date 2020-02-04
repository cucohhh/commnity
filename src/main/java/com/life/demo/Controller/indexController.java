package com.life.demo.Controller;

import com.life.demo.Model.User;
import com.life.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import reactor.netty.http.Cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/")
    public String index(HttpServletRequest request
                        ){
        Cookie[] cookies = request.getCookies();
        User user=null;
        if(cookies !=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                     user = userMapper.findByToken(token);

                    if(user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            return "index";
        }

        return "index";
    }
}
