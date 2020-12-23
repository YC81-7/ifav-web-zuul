package com.yc.ifav;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 这样feign可以扫描这个路径下的接口@Feignclient的接口，并生成动态代理对象
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients(basePackages = "com.yc.ifav.zuul")
// 这样feign可以扫描这个路径下的接口@Feignclient的接口，并生成动态代理对象
@EnableCircuitBreaker   //启用断路器
@SpringCloudApplication
public class IfavWebApp {

    public static void main(String[] args) {
        SpringApplication.run(IfavWebApp.class, args);
    }


}
