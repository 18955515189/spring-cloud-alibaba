package com.springcloud.alibaba.book.controller;

import com.springcloud.alibaba.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/getA")
    public String getA(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "getA";
    }

    @GetMapping("/add/{id}")
    public String addBook(@PathVariable("id") String id) throws InterruptedException {

        bookService.addBookById(id);
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

    @GetMapping("/addFromAuthor2/{id}")
    public String addFromAuthor2(@PathVariable("id") String id){
        Integer realID = Integer.parseInt(id)+1;
        bookService.addBookById(realID+"");

        return SUCCESS_CODE;
    }

}
