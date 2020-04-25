package com.springcloud.alibaba.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.alibaba.book.entity.Book;
import com.springcloud.alibaba.book.mapper.BookMapper;
import com.springcloud.alibaba.book.service.BookService;
import com.springcloud.alibaba.feignclient.ComsumerBFeignClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private ComsumerBFeignClient comsumerBFeignClient;


    @Override
    @Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "ComsumerAFeignClient")
    public void addBookById(String id) {
        String xid = RootContext.getXID();
        log.info("RootContext xid : "+xid);
        Book book = new Book();
        book.setId(new BigDecimal(id));
        book.setTitle("1");
        bookMapper.insert(book);
        if(id.equals("211")||id.equals("21")){
            throw new RuntimeException("自定义异常");
        }
    }
}
