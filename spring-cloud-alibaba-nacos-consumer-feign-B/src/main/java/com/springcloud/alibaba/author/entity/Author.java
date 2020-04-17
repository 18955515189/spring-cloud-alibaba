package com.springcloud.alibaba.author.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author weizhouck
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("author")
@ApiModel(value="Author对象", description="")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private BigDecimal yearOfBirth;

    private BigDecimal distinguished;


}
