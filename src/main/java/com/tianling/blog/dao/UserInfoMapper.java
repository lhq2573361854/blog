package com.tianling.blog.dao;

import com.tianling.blog.beans.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianling.blog.config.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@CacheNamespace(implementation=MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     *  通过账号获取
     * @param account
     * @return
     */

    public boolean getExistByAccount(String account);

}
