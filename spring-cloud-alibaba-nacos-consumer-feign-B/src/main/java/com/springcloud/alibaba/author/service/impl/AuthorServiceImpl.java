package com.springcloud.alibaba.author.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.alibaba.author.entity.Author;
import com.springcloud.alibaba.author.mapper.AuthorMapper;
import com.springcloud.alibaba.author.service.AuthorService;
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
 * @since 2020-04-16
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

    @Resource
    private AuthorMapper authorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFromId(String id) {
        Author author = new Author();
        author.setId(new BigDecimal(id));
        author.setFirstName(id);
        author.setLastName(id);
        authorMapper.insert(author);

        if(id.equals("98")||id.equals("985")){
            throw new RuntimeException("报错啦！");
        }
    }
}
