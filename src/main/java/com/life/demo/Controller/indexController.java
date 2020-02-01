package com.life.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index(){return "index";}
}
