package com.tianling.blog.controller.back;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianling.blog.beans.ArticleInfo;
import com.tianling.blog.beans.Response;
import com.tianling.blog.beans.UserInfo;
import com.tianling.blog.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.naming.Name;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


/**
 * @author TianLing
 * Date 2020/5/8 22:37
 * Description
 */
@Controller
@Slf4j
@RequestMapping("back/user")
public class UserInfoController {
    @Autowired
    UserInfoService  userInfoService;

    @RequestMapping("list")
    public String list(UserInfo userInfo,Model model,@RequestParam(required = false) String currentIndex){
        Page<UserInfo> page = new Page<>();
        page.setSize(5);
        if(currentIndex!=null){
            page.setCurrent(Integer.parseInt(currentIndex));
            System.out.println("currentIndex = " + currentIndex);
            model.addAttribute("currentIndex",Integer.parseInt(currentIndex));
        }else{
            model.addAttribute("currentIndex",1);
        }
        Page<UserInfo> page1 = userInfoService.page(page, new QueryWrapper<UserInfo>(userInfo));
        model.addAttribute("user",page1);
        model.addAttribute("userInfo",userInfo);
        return "/back/userinfo/userinfo_list";
    }

    @RequestMapping("loadAdd")
    public String loadAdd(UserInfo userInfo,Model model){
        System.out.println("userInfo = " + userInfo);
        if(userInfo != null){
            model.addAttribute("userInfo", userInfo);
        }
        return "/back/userinfo/userinfo_add";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(UserInfo userInfo,Model model){
        Response response = new Response();
        if(userInfo == null || userInfo.getUserAccount() == null || userInfoService.getExistByAccount(userInfo.getUserAccount())){
            response.setData("失败");
            response.setState(400);
            return response;
        }

        userInfo.setUserMark("1");
        System.out.println("userInfo = " + userInfo);
        boolean save = userInfoService.save(userInfo);
        if(save){
            response.setData("成功");
            response.setState(200);
            return response;
        }
        response.setData("失败");
        response.setState(400);
        return response;
    }

    @ResponseBody
    @RequestMapping("/del")
    public Object delUser(@RequestParam HashMap map){
        Response response = new Response();
        System.out.println("map = " + map);
        boolean b = userInfoService.removeByMap(map);
        if(!b){
            response.setState(400);
            response.setData("删除失败");
            return response;
        }
        response.setState(200);
        response.setData("删除成功");
        return response;
    }

    @RequestMapping("login")
    public String userLogin(UserInfo userInfo, Model model, HttpSession session){
        System.out.println(userInfo);
        Integer count = userInfoService.query().eq("user_name", userInfo.getUserName())
                .eq("user_password", userInfo.getUserPassword()).count();


        if(count > 0){
            Object entity = userInfoService.query().eq("user_name", userInfo.getUserName())
                    .eq("user_password", userInfo.getUserPassword()).one();
            UserInfo userInfo1;
            if ("com.alibaba.fastjson.JSONObject".equals(entity.getClass().getName())){
                String s = JSONObject.toJSONString(entity);
                userInfo1 = JSON.parseObject(s, UserInfo.class);
                session.setAttribute("info", userInfo1);
            }else{
                session.setAttribute("info", (UserInfo)entity);
            }
            return "redirect:/back/index";
        }
        return "redirect:/back/login";
    }
}
