package com.yc.ifav.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.yc.ifav.zuul.MyFavClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public class MyFavsRestService {
    @Autowired
    private MyFavClient favClient;


    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(int muid) {
        return favClient.findAll(muid);
    }

    private String findAllFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }



    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(int muid, String mfname, String mfurl,String mfdesc) {
        return favClient.create(muid, mfname, mfurl, mfdesc);
    }

    private String createFallback(int muid, String mfname, String mfurl,String mfdesc) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加"+mfname);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return favClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }
}
