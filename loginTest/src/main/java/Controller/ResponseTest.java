package main.java.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 5秒后重定向
@WebServlet("/redirect")
public class ResponseTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //案例3秒自动跳转到新页面
        PrintWriter out = response.getWriter();
        out.println("<span>5</span>秒后自动跳转    <a href='index.jsp' >直接跳转</a>");
        out.println("<script>");
        out.println("let span = document.getElementsByTagName('span')[0];");
        out.println("setInterval(function () {");
        out.println("if (span.innerText > 0) {");
        out.println("span.innerText = --span.innerText;}");
        out.println("}, 1000)");
        out.println("</script>");

        //设置响应状态码
        response.setStatus(302);
        //设置刷新 3秒
        response.setHeader("refresh", "5;url=index.html");
//        response.sendRedirect("index.html");
    }
}
