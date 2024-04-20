package com.tourism.toursandtravel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/get")
    public String doGet(){
        return "Hello, ji for starting this project";

    }

    @PostMapping("/post")
    public String doPost(){
        return  "Hello ji, This is the post mapping ";

    }



}
