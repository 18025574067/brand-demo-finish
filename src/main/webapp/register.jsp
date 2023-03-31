<%--
  Created by IntelliJ IDEA.
  User: hyb
  Date: 2023/3/28
  Time: 下午7:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.html">登录</a>
    </div>
    <form id="reg-form" action="/brand-demo-finish/registerServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg">${register_msg}</span>
<%--                    <span id="username_err" class="err_msg">该用户名己被注册</span>--%>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="/brand-demo-finish/checkCodeServlet">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>
    document.getElementById("changeImg").onclick = function (){
        document.getElementById("checkCodeImg").src="/brand-demo-finish/checkCodeServlet?" + new Date().getMilliseconds();
    }
</script>

<script>
    // 1. 给用户名输入框绑定：失去焦点
    document.getElementById("username").onblur = function () {
        // 2. 发送ajax请求
        // 获取用户名的值
        var username = this.value;

        // 2.1. 创建核心对象
        var xhttp;
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        // 2.2. 发送请求
        xhttp.open("GET", "http://localhost:8080/brand-demo-finish/selectUserServlet?username=" + username);
        xhttp.send();

        // 2.3. 获取响应
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // alert(this.responseText);
                // 判断
                if (this.responseText == "true"){
                    // 用户名存在, 显示提示
                    document.getElementById("username_err").style.display = '';
                }else {
                    // 用户名不存在，清除提示信息
                    document.getElementById("username_err").style.display = 'none';

                }
            }
        }
    }
</script>
</body>
</html>