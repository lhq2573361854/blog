package com.tianling.blog.dao;

import com.tianling.blog.config.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author TianLing
 * Date 2020/5/10 19:59
 * Description
 */
@CacheNamespace(implementation=MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface BasicInfoMapper {

    /**
     * 获取数据库信息
     * @return
     */
    public String getDBVersion();
}
