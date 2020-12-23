package com.yc.ifav.zuul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="BASE-IFAV-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)
public interface MyFavClient {
    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myfavs-proxy/myfavs/findById",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("muid") int muid);


    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myfavs-proxy/myfavs/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestParam("muid") int muid,@RequestParam("mfname") String mfname,@RequestParam("mfurl") String mfurl,@RequestParam("mfdesc") String mfdesc);

    @RequestMapping(method = RequestMethod.DELETE, value = "/yc-api/myfavs-proxy/myfavs/delete")
    String delete(@RequestParam("mid") int mid);
}