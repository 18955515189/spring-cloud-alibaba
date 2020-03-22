package com.springcloud.alibaba.book.service.impl;

import com.springcloud.alibaba.book.entity.Book;
import com.springcloud.alibaba.book.mapper.BookMapper;
import com.springcloud.alibaba.book.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
