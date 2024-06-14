package org.codewithcaleb.springsecuritymarathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    /** cannot work unless i have enabled CSRF **/
    /**It is only the getMethod that can work before i enable CSRF*/
    @PostMapping("/demo")
    public String demo(){
        return "demo";
    }
}
