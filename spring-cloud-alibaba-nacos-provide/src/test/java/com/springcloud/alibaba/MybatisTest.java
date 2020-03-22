package com.springcloud.alibaba;

import com.springcloud.alibaba.book.entity.Book;
import com.springcloud.alibaba.book.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TODO
 * @since 2020/3/21 0021 21:07
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private BookService bookService;

    @Test
    public void test1(){
        Book book = bookService.getById(1);
        System.out.println(book);
    }
    @Test
    public void test2(){
        Book book = new Book();
        book.setId(new BigDecimal(10));
        bookService.save(book);
        System.out.println(book);
    }
}
