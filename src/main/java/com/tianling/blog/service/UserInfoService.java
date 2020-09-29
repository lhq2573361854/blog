package com.tianling.blog.service;

import com.tianling.blog.beans.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     *通过账号获取
     * @param account
     * @return
     */
    public boolean getExistByAccount(String account);


}
