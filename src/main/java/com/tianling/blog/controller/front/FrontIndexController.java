package com.tianling.blog.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianling.blog.beans.ArticleInfo;
import com.tianling.blog.beans.CategoryInfo;
import com.tianling.blog.beans.MessageInfo;
import com.tianling.blog.elasticsearch.dao.ArticleInfoRepository;
import com.tianling.blog.service.ArticleInfoService;
import com.tianling.blog.service.CategoryInfoService;
import com.tianling.blog.service.MessageInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author TianLing
 * Date 2020/5/22 14:06
 * Description
 */
@Controller
@Slf4j
public class FrontIndexController {
    @Autowired
    CategoryInfoService categoryInfoService;
    @Autowired
    ArticleInfoService articleInfoService;
    @Autowired
    MessageInfoService messageInfoService;
    @Autowired
    ArticleInfoRepository articleInfoRepository;
    @RequestMapping({"/index","/"})
    public String index(Model model){
        List<CategoryInfo> categoryList = categoryInfoService.getCategoryList();
        QueryWrapper queryWrapper = new QueryWrapper();
        ArrayList arrayList = new ArrayList<String>();
        queryWrapper.orderByDesc("article_time");

        List<ArticleInfo> articleInfoList = articleInfoService.list(queryWrapper);

        if("com.alibaba.fastjson.JSONArray".equals(categoryList.getClass().getName())){
            String s = JSONObject.toJSONString(categoryList);
            categoryList = JSONArray.parseArray(s, CategoryInfo.class);
        }
        if("com.alibaba.fastjson.JSONArray".equals(articleInfoList.getClass().getName())){
            String s = JSONObject.toJSONString(articleInfoList);
            articleInfoList = JSONArray.parseArray(s, ArticleInfo.class);
        }


        for (ArticleInfo articleInfo : articleInfoList) {
            Integer categoryId = articleInfo.getCategoryId();
            String nameById = categoryInfoService.getNameById(categoryId);
            arrayList.add(nameById);
        }
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("articleInfoList",articleInfoList);
        model.addAttribute("categoryName",arrayList);
        return  "/front/index";
    }

    @RequestMapping("listview")
    public String listView(Integer id,String categoryName, Model model){
        Object articleInfo =articleInfoService.getById(id);

        List<CategoryInfo> categoryList = categoryInfoService.getCategoryList();
        if("com.alibaba.fastjson.JSONArray".equals(categoryList.getClass().getName())){
            String s = JSONObject.toJSONString(categoryList);
            categoryList = JSONArray.parseArray(s, CategoryInfo.class);
        }

        if ("com.alibaba.fastjson.JSONObject".equals(articleInfo.getClass().getName())){
            String s = JSONObject.toJSONString(articleInfoService.getById(id));
            articleInfo = JSON.parseObject(s, ArticleInfo.class);
        }

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("article", articleInfo);
        return "/front/listview";
    }

    @RequestMapping("list")
    public String list(String categoryName,String categoryId ,Model model,@RequestParam(required = false) Integer currentIndex){
        if(currentIndex == null){
            currentIndex = 1;
        }
        Page<ArticleInfo> objectPage = new Page<ArticleInfo>();
        objectPage.setCurrent(currentIndex);
        objectPage.setSize(7);
        QueryWrapper<ArticleInfo> articleInfoQueryWrapper = new QueryWrapper<>();
        articleInfoQueryWrapper.eq("category_id", categoryId);
        Page<ArticleInfo> page = articleInfoService.page(objectPage, articleInfoQueryWrapper);
        List<ArticleInfo> records = page.getRecords();
        if(records.getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
            String s = JSONObject.toJSONString(records);
            records = JSONArray.parseArray(s, ArticleInfo.class);
        }
        page.setRecords(records);
        List<CategoryInfo> categoryList = categoryInfoService.getCategoryList();
        if("com.alibaba.fastjson.JSONArray".equals(categoryList.getClass().getName())){
            String s = JSONObject.toJSONString(categoryList);
            categoryList = JSONArray.parseArray(s, CategoryInfo.class);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("articlePage", page);
        model.addAttribute("categoryName", categoryName);
        return "/front/list";
    }

    @RequestMapping("message")
    public String message(Model model){
        QueryWrapper<MessageInfo> messageInfoQueryWrapper = new QueryWrapper<>();
        messageInfoQueryWrapper.eq("message_mark", 1);
        List<MessageInfo> messageList = messageInfoService.list(messageInfoQueryWrapper);
        if("com.alibaba.fastjson.JSONArray".equals(messageList.getClass().getName())){
            String s = JSONObject.toJSONString(messageList);
            messageList = JSONArray.parseArray(s, MessageInfo.class);
        }
        List<CategoryInfo> categoryList = categoryInfoService.getCategoryList();
        if("com.alibaba.fastjson.JSONArray".equals(categoryList.getClass().getName())){
            String s = JSONObject.toJSONString(categoryList);
            categoryList = JSONArray.parseArray(s, CategoryInfo.class);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("messageList", messageList);
        return "/front/message";
    }

    @ResponseBody
    @RequestMapping("/message/add")
    public String add(MessageInfo messageInfo) {
        LocalDateTime now = LocalDateTime.now();
        messageInfo.setMessageTime(now);
        boolean save = messageInfoService.save(messageInfo);
        if (save){
            return "200";
        }else{

            return "404";
        }
    }
    @RequestMapping("search")
    @ResponseBody
    public List<ArticleInfo>  search(){
        return articleInfoRepository.findArticleInfoByArticleId(60);
    }
}
