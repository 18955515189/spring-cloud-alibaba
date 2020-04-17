package com.springcloud.alibaba.author.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.alibaba.author.entity.Author;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weizhouck
 * @since 2020-04-16
 */
@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

}
