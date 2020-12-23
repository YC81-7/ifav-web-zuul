package com.yc.ifav.future;


import com.yc.ifav.service.MyFavsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class MyFavsFuture {

    @Autowired
    private MyFavsRestService favRestService;

    @Async
    public CompletableFuture<String> findAll(int muid) {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return favRestService.findAll(muid);
        });
    }

    @Async
    public CompletableFuture<String> create(int muid, String mfname, String mfurl,String mfdesc) {
        return CompletableFuture.supplyAsync(() -> {
            return favRestService.create(muid, mfname, mfurl, mfdesc);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return favRestService.delete(id);
        });
    }
}
