package com.tianling.blog.service.impl;

import com.tianling.blog.beans.ArticleInfo;
import com.tianling.blog.dao.ArticleInfoMapper;
import com.tianling.blog.service.ArticleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@Service

public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements ArticleInfoService {

}
