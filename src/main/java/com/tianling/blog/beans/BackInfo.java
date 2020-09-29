package com.tianling.blog.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author TianLing
 * Date 2020/5/10 19:41
 * Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackInfo implements Serializable {
    private static final long serialVersionUID=1L;
    private int userCount;
    private String ip;
    private String loginTime;
    private String DBVersion;
}
