package com.tianling.blog.dao;

import com.tianling.blog.beans.CategoryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianling.blog.config.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@CacheNamespace(implementation=MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface CategoryInfoMapper extends BaseMapper<CategoryInfo> {
    /**
     * 获取分类列表信息
     * @return
     */
    List<CategoryInfo> getCategoryList();
    /**
     * 通过id返回name
     * @param id
     * @return
     */
    String getNameById(Integer id);

}
