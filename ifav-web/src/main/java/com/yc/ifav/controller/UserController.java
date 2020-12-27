package com.yc.ifav.controller;

import com.yc.ifav.zuul.UserClient;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserClient userFeign;


    @Async
    @PostMapping("/login")
    public CompletableFuture<String> login(@Param("uName")String uName, @Param("uPwd")String uPwd, @Param("uEmail") String uEmail ) {

        return CompletableFuture.supplyAsync(() -> {

           return userFeign.login(uName,uPwd,uEmail);
        });
    }


    @RequestMapping("/vcode")
    public void sendVcode(@Param("uEmail") String uEmail) {
        userFeign.sendVcode(uEmail);
    }


    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public void uploadPic(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        userFeign.uploadPic(multipartFile,request,response);
    }


    @PostMapping("/register")
    public CompletableFuture<String> register(@RequestParam("uName")String uName,@RequestParam("uPwd") String uPwd, @RequestParam("vkey") String vkey){
        return CompletableFuture.supplyAsync(() -> {
            return userFeign.register(uName, uPwd, vkey);
        });
    }

}
