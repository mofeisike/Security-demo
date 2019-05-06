package com.mofei.securitythyboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mofeiske
 * @Description: ${todo}
 * @Date: Create in 2019/5/5 11:17
 */

@Controller
public class MainController {
    
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
}
