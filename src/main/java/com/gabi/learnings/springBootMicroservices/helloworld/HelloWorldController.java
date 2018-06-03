package com.gabi.learnings.springBootMicroservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

    //GET
    //URI - /hello-world
    //method - "Hello World"
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    //GET
    //URI - /hello-world-bean
    //method - "Hello World bean"
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldreturnBean(){
        return new HelloWorldBean("Hello World");
    }
}
