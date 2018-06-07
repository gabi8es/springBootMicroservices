package com.gabi.learnings.springBootMicroservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
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


    //GET
    //URI - /hello-world
    //method - "Hello World"
    @GetMapping(path = "/hello-world-internazionalized")
    //public String helloWorldInt(@RequestHeader(name="Accept-Language", required = false) Locale locale){
    public String helloWorldInt(){

        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
