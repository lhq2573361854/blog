package com.tianling.blog.config;



import com.tianling.blog.beans.UserInfo;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author TianLing
 * Date 2020/5/10 19:16
 * Description
 */
@WebFilter("/back/*")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        UserInfo info = (UserInfo)session.getAttribute("info");
        if (requestURI.contains("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(info  != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            servletRequest.getRequestDispatcher("/back/login").forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
