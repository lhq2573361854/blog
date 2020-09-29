package com.tianling.blog.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author TianLing
 * Date 2020/5/10 13:20
 * Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID=1L;
    int state;
    String data;
}
