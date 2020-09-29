package com.tianling.blog.service.impl;

import com.tianling.blog.dao.BasicInfoMapper;
import com.tianling.blog.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author TianLing
 * Date 2020/5/10 20:02
 * Description
 */
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
   @Autowired
   BasicInfoMapper basicInfoMapper;
    /**
     * 获取数据库信息
     *
     * @return
     */
   // @Cacheable(value = "DBVersion",key = "methodName")
    @Override
    public String getDBVersion() {
        return basicInfoMapper.getDBVersion();
    }
}
