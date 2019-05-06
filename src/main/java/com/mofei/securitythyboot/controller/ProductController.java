package com.mofei.securitythyboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author mofeiske
 * @Description: ${todo}
 * @Date: Create in 2019/5/5 10:23
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/add")
    public String add(){
        return "product/add";
    }

    @GetMapping("/update")
    public String update(){
        return "product/update";
    }


    @GetMapping("/list")
    public String list(){
        return "product/list";
    }


    @GetMapping("/delete")
    public String delete(){
        return "product/delete";
    }

    /*@RequestMapping("/404")
    public String error404() {
        return "commons/404";
    }*/

    /*@RequestMapping("/403")
    public String error403() {
        return "commons/403";
    }*/


}
