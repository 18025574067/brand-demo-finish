package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;

        // 1. 判断session中是否有user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        // 2. 判断user是否为空
        if (user != null){
            // 登录过了
            // 放行
            chain.doFilter(request, response);
        }else {
            // 没有登录过，存储登录信息，跳转到登录页面
            req.setAttribute("login_msg", "您尚未登录~~~");
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
