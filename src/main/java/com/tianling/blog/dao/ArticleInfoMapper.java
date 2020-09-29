package com.tianling.blog.dao;

import com.tianling.blog.beans.ArticleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianling.blog.config.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo> {

}
