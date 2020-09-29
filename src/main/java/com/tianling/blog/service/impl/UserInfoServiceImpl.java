package com.tianling.blog.service.impl;

import com.tianling.blog.beans.UserInfo;
import com.tianling.blog.dao.UserInfoMapper;
import com.tianling.blog.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    /**
     * 通过账号获取
     * @param account
     * @return
     */
    @Override
    public boolean getExistByAccount(String account) {
        return userInfoMapper.getExistByAccount(account);
    }


}
