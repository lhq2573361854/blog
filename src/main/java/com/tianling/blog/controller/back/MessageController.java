package com.tianling.blog.controller.back;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianling.blog.beans.ArticleInfo;
import com.tianling.blog.beans.MessageInfo;
import com.tianling.blog.beans.UserInfo;
import com.tianling.blog.service.MessageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author TianLing
 * Date 2020/5/8 23:02
 * Description
 */
@Controller
@RequestMapping("/back/message")
public class MessageController {
    @Autowired
    MessageInfoService messageInfoService;
    @RequestMapping("list")
    public String list(MessageInfo messageInfo, @RequestParam(required = false) String currentIndex, Model model) {
        Page page = new Page<ArticleInfo>();
        page.setSize(7);

        if(currentIndex!=null){
            page.setCurrent(Integer.parseInt(currentIndex));
            model.addAttribute("currentIndex",Integer.parseInt(currentIndex));
        }else{
            model.addAttribute("currentIndex",1);
        }
        Page<MessageInfo> messageInfoPage = messageInfoService.page(page);
        List<MessageInfo> records = messageInfoPage.getRecords();
        if(records.getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
            String s = JSONObject.toJSONString(records);
            records = JSONArray.parseArray(s, MessageInfo.class);
        }
        messageInfoPage.setRecords(records);

        model.addAttribute("messageLists", messageInfoPage);

        return "/back/message/message_list";
    }

    @RequestMapping("show")
    @ResponseBody
    public String update(@RequestParam("id") Integer id,@RequestParam("value") String value) {
        boolean update = messageInfoService.update().eq("message_id", id).set("message_mark", value).update();
        if(update){
            return  "success";
        }
        return "error";

    }
    @RequestMapping("del")
    @ResponseBody
    public String del(Integer id) {
        boolean b = messageInfoService.removeById(id);
        if(!b){
         return "error";
        }else{
            return "success";
        }

    }

}
