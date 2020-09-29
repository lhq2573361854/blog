package com.tianling.blog.service.impl;

import com.tianling.blog.beans.CategoryInfo;
import com.tianling.blog.dao.CategoryInfoMapper;
import com.tianling.blog.service.CategoryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@Service
public class CategoryInfoServiceImpl extends ServiceImpl<CategoryInfoMapper, CategoryInfo> implements CategoryInfoService {
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    /**
     *
     * @return
     */
    @Cacheable(value = "CategoryList",key = "methodName")
    @Override
    public List<CategoryInfo> getCategoryList() {
        return categoryInfoMapper.getCategoryList();
    }

    /**
     * 通过id返回name
     *
     * @param id
     * @return
     */
    @Override
    //@Cacheable(value = "CategoryList",key = "#p0")
    public String getNameById(Integer id) {
        return categoryInfoMapper.getNameById(id);
    }
}
