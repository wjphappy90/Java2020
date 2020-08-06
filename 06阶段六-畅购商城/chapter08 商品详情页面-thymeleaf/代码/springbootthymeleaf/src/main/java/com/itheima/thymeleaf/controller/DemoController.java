package com.itheima.thymeleaf.controller;

import com.itheima.thymeleaf.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test")
    public String test(Model model,String id){
        System.out.println(id);

        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"zs","bj"));
        userList.add(new User(2,"ls","hlj"));
        userList.add(new User(3,"ww","haerbin"));
        model.addAttribute("userList",userList);

        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("No","123");
        dataMap.put("address","bj");
        model.addAttribute("dataMap",dataMap);

        String[] names = {"张三","李四","王五"};
        model.addAttribute("names",names);

        model.addAttribute("now",new Date());

        model.addAttribute("age",25);


        model.addAttribute("hello","hello world");
        return "demo";
    }
}
