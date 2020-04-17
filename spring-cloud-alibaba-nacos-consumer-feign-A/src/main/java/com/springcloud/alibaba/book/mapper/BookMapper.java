package com.springcloud.alibaba.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.alibaba.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weizhouck
 * @since 2020-03-21
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
