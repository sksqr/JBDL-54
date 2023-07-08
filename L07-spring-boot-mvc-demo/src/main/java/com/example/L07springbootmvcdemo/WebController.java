package com.example.L07springbootmvcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@RequestMapping("")
public class WebController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    @ResponseBody
    public String getWebPage(){
        return "hello";
    }

    @GetMapping("/static")
    public String getStaticMenu(){
        //return "static-menu.html";
        return "menu-with-js.html";
    }


    @GetMapping("/menu")
    public ModelAndView getMenu(){
        ModelAndView modelAndView = new ModelAndView("dynamic-menu.html");
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss");
        modelAndView.getModelMap().put("serverTime",dateFormat.format(new Date()));
        modelAndView.getModelMap().put("products",productService.getAllProduct());
        return modelAndView;
    }



}
