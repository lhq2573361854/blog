package com.tianling.blog.controller.back;

import com.tianling.blog.beans.BackInfo;
import com.tianling.blog.service.BasicInfoService;
import com.tianling.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author TianLing
 * Date 2020/5/8 21:24
 * Description
 */
@Controller
@RequestMapping("back")
public class AdminIndexController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    BasicInfoService basicInfoService;
    @RequestMapping("login")
    public String login(HttpSession session){
        if(session.getAttribute("info")!=null){
            session.setAttribute("info",null);
        }
        return "/back/login";
    }
    @RequestMapping("index")
    public String index(){
        return "/back/index";
    }
    @RequestMapping("main")
    public String main(Model model, HttpServletRequest request){
        Integer count = userInfoService.query().count();
        String ip = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(now);
        String dbVersion = basicInfoService.getDBVersion();


        BackInfo backInfo = new BackInfo();
        backInfo.setDBVersion(dbVersion);
        backInfo.setIp(ip);
        backInfo.setLoginTime(format);
        backInfo.setUserCount(count);
        model.addAttribute("backInfo", backInfo);
        return "/back/main";
    }

}
