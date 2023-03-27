package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 获取复选框数据
        String remember = request.getParameter("remember");

        // 2. 调用service方法查询User
        User user = service.login(username, password);

        // 3. 判断User是否为null
        if (user == null){
            // 登录失败
            // 存储错误信息到request
            request.setAttribute("login_msg", "用户名或密码错误");

            // 跳转到login.jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }else {
            // 登录成功，
            // 判断用户是否勾选 记住我 (字符串写前面，防止出现空值异常)
            if ("1".equals(remember)){
                // 勾选了

                // 创建cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_psssword = new Cookie("password", password);

                // 设置cookie的存活时间
                int time = 60*60*24*7;
                c_username.setMaxAge(time);
                c_psssword.setMaxAge(time);

                // 发送cookie
                response.addCookie(c_username);
                response.addCookie(c_psssword);

            }
            // 将登陆成功后的用户对象，存储到session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 跳转到查询所有BrandServlet
            String path = request.getContextPath();
            response.sendRedirect(path + "/selectAllServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
}
