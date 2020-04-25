package com.springcloud.alibaba.author.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.alibaba.author.entity.Author;
import com.springcloud.alibaba.author.mapper.AuthorMapper;
import com.springcloud.alibaba.author.service.AuthorService;
import com.springcloud.alibaba.feignclient.ComsumerAFeignClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

    @Resource
    private AuthorMapper authorMapper;

    @Resource
    private ComsumerAFeignClient comsumerAFeignClient;

    @Override
    @Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "ComsumerBFeignClient")
    public void saveFromId(String id) {
        //第一步
        comsumerAFeignClient.addFromAuthor(id);

        //第二步
        String xid = RootContext.getXID();
        log.info("RootContext xid : "+xid);
        Author author = new Author();
        author.setId(new BigDecimal(id));
        author.setFirstName(id);
        author.setLastName(id);
        authorMapper.insert(author);
        //第三步
        comsumerAFeignClient.addFromAuthor2(id);

        if(id.equals("98")||id.equals("985")){
            throw new RuntimeException("报错啦！");
        }
    }
}
