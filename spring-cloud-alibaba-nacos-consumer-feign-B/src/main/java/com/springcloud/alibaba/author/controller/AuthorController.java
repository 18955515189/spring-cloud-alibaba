package com.springcloud.alibaba.author.controller;


import com.springcloud.alibaba.author.entity.Author;
import com.springcloud.alibaba.author.service.AuthorService;
import com.springcloud.alibaba.feignclient.ComsumerAFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weizhouck
 * @since 2020-04-16
 */
@RestController
public class AuthorController {

    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String ERROR_CODE = "ERROR";

    @Autowired
    private AuthorService authorService;

    @Resource
    private ComsumerAFeignClient comsumerAFeignClient;

    @GetMapping("/add/{id}")
    public String addAuthor(@PathVariable("id") String id){
        authorService.saveFromId(id);
        return SUCCESS_CODE;
    }

    @GetMapping("/addAll/{id}")
    @GlobalTransactional(timeoutMills = 300000, name = "ComsumerBFeignClient")
    public String addAll(@PathVariable("id") String id){
        authorService.saveFromId(id);
        comsumerAFeignClient.addFromAuthor(id);
        return SUCCESS_CODE;
    }

}
