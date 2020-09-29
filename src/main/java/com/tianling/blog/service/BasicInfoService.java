package com.tianling.blog.service;

import org.springframework.cache.annotation.Cacheable;

/**
 * @author TianLing
 * Date 2020/5/10 20:01
 * Description
 */
public interface BasicInfoService {
    /**
     * 获取数据库基本信息
     * @return
     */
    public String getDBVersion();
}
