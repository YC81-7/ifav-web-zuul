package com.yc.ifav.controller;


import com.yc.ifav.zuul.WetherClient;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Resource
    private WetherClient weatherFegin;



    @Async
    @RequestMapping(method = RequestMethod.POST, value = "/getWeather")
    public CompletableFuture<String> get(@Param("province") String province, @Param("city") String city){
        return CompletableFuture.supplyAsync(() -> {
            return weatherFegin.get(province,city);
        });
    }

}
