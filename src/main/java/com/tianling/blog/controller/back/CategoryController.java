package com.tianling.blog.controller.back;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tianling.blog.beans.CategoryInfo;
import com.tianling.blog.service.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author TianLing
 * Date 2020/5/8 22:55
 * Description
 */
@Controller
@RequestMapping("/back/category")
public class CategoryController {
    @Autowired
    CategoryInfoService categoryInfoService;
    @RequestMapping("/list")
    public String list(Model model){
        List<CategoryInfo> categoryList = categoryInfoService.getCategoryList();

        if("com.alibaba.fastjson.JSONArray".equals(categoryList.getClass().getName())){
            String s = JSONObject.toJSONString(categoryList);
            categoryList = JSONArray.parseArray(s, CategoryInfo.class);
        }
        for (CategoryInfo categoryInfo : categoryList) {
            System.out.println(categoryInfo);
        }
        model.addAttribute("categoryInfos",categoryList);
        return "/back/category/category";
    }
    @RequestMapping("/loadUpdate")
    public String update(CategoryInfo categoryInfo,Model model){
        model.addAttribute("category", categoryInfo);
        return "/back/category/category_update";
    }

    @RequestMapping("/update")
    public String update(CategoryInfo categoryInfo){
        CategoryInfo categoryInfo1 = new CategoryInfo();
        categoryInfo1.setCategoryId(categoryInfo.getCategoryId());
        boolean b = categoryInfoService.update(categoryInfo,new UpdateWrapper<>(categoryInfo1));
        return "redirect:/back/category/list";
    }


    @RequestMapping("/add")
    public String add(CategoryInfo categoryInfo,Model model){
        boolean save = categoryInfoService.save(categoryInfo);
        System.out.println("save = " + categoryInfo.getCount());
        if (save) {
            return "redirect:/back/category/list";
        }
        return "redirect:/back/category/list";
    }

    @RequestMapping("/del")
    public String delete(Integer categoryId){
        System.out.println("categoryId = " + categoryId);
        boolean b = categoryInfoService.removeById(categoryId);
        return "redirect:/back/category/list";
    }

}
