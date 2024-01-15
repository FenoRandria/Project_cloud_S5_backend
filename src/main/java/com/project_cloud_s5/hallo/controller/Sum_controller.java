package com.project_cloud_s5.hallo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sum_controller 
{
    @GetMapping("/api/somme")
    public int somme(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
    
    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}