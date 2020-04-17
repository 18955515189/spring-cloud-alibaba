package com.springcloud.alibaba.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.alibaba.book.entity.Book;
import com.springcloud.alibaba.book.mapper.BookMapper;
import com.springcloud.alibaba.book.service.BookService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weizhouck
 * @since 2020-03-21
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBookById(String id) {
        Book book = new Book();
        book.setId(new BigDecimal(id));
        book.setTitle("1");
        bookMapper.insert(book);

        if(id.equals("211")||id.equals("21")){
            throw new RuntimeException("自定义遗产");
        }
    }
}
