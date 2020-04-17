package com.springcloud.alibaba.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.alibaba.book.entity.Book;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weizhouck
 * @since 2020-03-21
 */
public interface BookService extends IService<Book> {

    public void addBookById(String id);

}
