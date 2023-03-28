package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 2. 调用service注册
        boolean flag = service.register(user);
        // 3. 判断注册是否成功
        if (flag){
            // 注册功能，跳转登陆页面
            request.setAttribute("register_msg", "注册成功，请登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            // 注册失败，跳转到注册页面
            request.setAttribute("register_msg", "用户名己存在。。。");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
}
