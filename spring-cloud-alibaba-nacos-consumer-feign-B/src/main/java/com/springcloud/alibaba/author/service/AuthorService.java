package com.springcloud.alibaba.author.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.alibaba.author.entity.Author;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weizhouck
 * @since 2020-04-16
 */
public interface AuthorService extends IService<Author> {

    void saveFromId(String id);

}
