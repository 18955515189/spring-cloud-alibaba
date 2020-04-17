package com.springcloud.alibaba.book.controller;

import com.springcloud.alibaba.book.service.BookService;
import com.springcloud.alibaba.feignclient.ComsumerBFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weizhouck
 * @since 2020-03-21
 */
@RestController
public class BookController {

    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String ERROR_CODE = "ERROR";

    @Autowired
    private BookService bookService;

    @Resource
    private ComsumerBFeignClient comsumerBFeignClient;

    @GetMapping("/add/{id}")
    @GlobalTransactional(timeoutMills = 300000, name = "ComsumerAFeignClient")
    public String addBook(@PathVariable("id") String id) throws InterruptedException {

        bookService.addBookById(id);
        comsumerBFeignClient.addAuthor(id);
        Thread.sleep(10000);
        if(id.equals("21")){
            throw new RuntimeException("21啦");
        }
        return SUCCESS_CODE;
    }

    @GetMapping("/addFromAuthor/{id}")
    public String addFromAuthor(@PathVariable("id") String id){

        bookService.addBookById(id);

        return SUCCESS_CODE;
    }

}
