package com.tianling.blog.controller.back;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianling.blog.beans.ArticleInfo;
import com.tianling.blog.beans.CategoryInfo;
import com.tianling.blog.beans.UserInfo;
import com.tianling.blog.elasticsearch.dao.ArticleInfoRepository;
import com.tianling.blog.service.ArticleInfoService;
import com.tianling.blog.service.CategoryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author TianLing
 * Date 2020/5/8 22:48
 * Description
 */
@Controller
@Slf4j
@RequestMapping("/back/article")
public class ArticleController {
    @Autowired
    ArticleInfoService articleInfoService;
    @Autowired
    CategoryInfoService categoryInfoService;
    @Autowired
    ArticleInfoRepository articleInfoRepository;

    @RequestMapping("/list")
    public String list(ArticleInfo articleInfo, @RequestParam(required = false) String currentIndex,Model model){
        int i=0;
        String articleTitle=null;
        Page<ArticleInfo> page = new Page<>();
        page.setSize(7);
        if (articleInfo != null) {
            articleTitle = articleInfo.getArticleTitle();
        }
        if(currentIndex!=null){
            page.setCurrent(Integer.parseInt(currentIndex));
            model.addAttribute("currentIndex",Integer.parseInt(currentIndex));
        }else{
            model.addAttribute("currentIndex",1);
        }
        QueryWrapper<ArticleInfo> articleInfoQueryWrapper = new QueryWrapper<>();
        if (articleTitle != null) {
            articleInfoQueryWrapper.like("article_title", articleTitle);
        }
        Page<ArticleInfo> articleInfoPage = articleInfoService.page(page,articleInfoQueryWrapper);
        String[] categoryList = new String[(int)articleInfoPage.getSize()];
        List<ArticleInfo> records = articleInfoPage.getRecords();
        if(records.getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
            String s = JSONObject.toJSONString(records);
            records = JSONArray.parseArray(s, ArticleInfo.class);
        }
        articleInfoPage.setRecords(records);
        for (ArticleInfo articleInfo1 : articleInfoPage.getRecords()) {
            categoryList[i] = categoryInfoService.getNameById(articleInfo1.getCategoryId());
            i++;
        }
        model.addAttribute("categoryLists", categoryList);
        model.addAttribute("articleTitle", articleTitle);
        model.addAttribute("articlePage", articleInfoPage);
        return "/back/article/article_list";
    }
    @RequestMapping("/loadAdd")
    public String loadAdd(Model model){
        List<CategoryInfo> list = categoryInfoService.getCategoryList();
        model.addAttribute("categoryLists", list);
        return "/back/article/article_add";
    }
    @RequestMapping("/loadupdate")
    public String loadUpdate(ArticleInfo articleInfo,Model model){
        List<CategoryInfo> list = categoryInfoService.getCategoryList();
        model.addAttribute("categoryLists", list);
        model.addAttribute("articleInfo",articleInfo);
        System.out.println("articleInfo = " + articleInfo);
        return "/back/article/article_update";
    }
    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer id){
        boolean b = articleInfoService.removeById(id);
        if(b){
            return "200";
        }else{
            return "error";
        }
    }
    @RequestMapping("/update")
    public String update(ArticleInfo articleInfo,Model model,HttpSession session){
        System.out.println("articleInfo = " + articleInfo);
        List<CategoryInfo> list = categoryInfoService.getCategoryList();
        if (session!=null){
            LocalDateTime now = LocalDateTime.now();
            UserInfo userInfo = (UserInfo)session.getAttribute("info");
            articleInfo.setUserId(userInfo.getUserId());
            articleInfo.setArticleTime(now);
            boolean save = articleInfoService.update(articleInfo, new QueryWrapper<ArticleInfo>().eq("article_id", articleInfo.getArticleId()));
        }else{
            throw new RuntimeException("session 已经过期请重新登录");
        }
        model.addAttribute("categoryLists", list);
        model.addAttribute("articleInfo",articleInfo);
        return "/back/article/article_update";
    }
    @RequestMapping("/add")
    public String add(ArticleInfo articleInfo, HttpSession session){
        LocalDateTime now = LocalDateTime.now();
        if (session!=null){
            UserInfo userInfo = (UserInfo)session.getAttribute("info");
            articleInfo.setUserId(userInfo.getUserId());
            articleInfo.setArticleTime(now);
            boolean save = articleInfoService.save(articleInfo);
            articleInfoRepository.save(articleInfo);
            log.info(articleInfo.toString());
        }else{
            throw new RuntimeException("session 已经过期请重新登录");
        }
        System.out.println("articleInfo = " + articleInfo);
        return "redirect:/back/article/list";
    }
    @RequestMapping("/putfile")
    @ResponseBody
    public String putFile(@RequestParam("upload") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return "404";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "D:/file/upload/";
        fileName = IdUtil.simpleUUID() + suffixName;
        File tempFile = FileUtil.createTempFile(fileName,suffixName,new File(filePath),true);

        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ftp ftp = new Ftp("39.96.67.142");
        ftp.setMode(FtpMode.Passive);
        ftp.upload("backup/images/", tempFile);
        System.out.println("fileName+suffixName = " + fileName);
        ftp.close();

        return fileName;
    }

}
