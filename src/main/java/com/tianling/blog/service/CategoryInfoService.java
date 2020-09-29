package com.tianling.blog.service;

import com.tianling.blog.beans.CategoryInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
public interface CategoryInfoService extends IService<CategoryInfo> {
    /**
     * 返回所有category的集合
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
