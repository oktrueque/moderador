package com.oktrueque.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Envy on 6/7/2017.
 */
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getHomePage(){
        return "home";
    }


}
