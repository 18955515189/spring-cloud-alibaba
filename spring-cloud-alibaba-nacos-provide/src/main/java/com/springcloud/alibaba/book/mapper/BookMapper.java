package com.springcloud.alibaba.book.mapper;

import com.springcloud.alibaba.book.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
