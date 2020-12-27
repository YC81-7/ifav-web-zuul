package com.yc.ifav.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.ifav.zuul.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class UserRestService {
    @Autowired
    public  UserClient userClient;

    @HystrixCommand(fallbackMethod = "loginFallback")
    public String login(String uName,String uPwd, String uEmail) {
        return userClient.login(uName, uPwd, uEmail);
    }

    private String loginFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "registerFallback")
    public String register(String uName, String uPwd, String vkey) {
        return userClient.register(uName, uPwd, vkey);
    }

    private String registerFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/user-proxy/user/vcode",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    void sendVcode(@RequestParam("uEmail") String uEmail) ;
//
//    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/user-proxy/user/uploadPic",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public void uploadPic(@RequestParam("multipartFile") MultipartFile multipartFile, @RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) ;

    @HystrixCommand(fallbackMethod = "uploadPicFallback")
    public String uploadPic( MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        userClient.uploadPic(multipartFile, request, response);
        return "ok";
    }

    private String uploadPicFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "sendVcodeFallback")
    public String sendVcode(String uEmail) {
        userClient.sendVcode(uEmail);
        return "ok";
    }

    private String sendVcodeFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}
