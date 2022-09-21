package main.java.Controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 共享变量，统计访问人数
@WebServlet("/ServletContext")
public class ServletContextt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = this.getServletContext();
        Integer number = (Integer) sc.getAttribute("number");//获取number
        if (number == null) {
            //存放一个number
            sc.setAttribute("number", 1);
            System.out.println("初始化");
        } else {
            sc.setAttribute("number", number + 1);
            System.out.println("叠加");
        }
        System.out.println(sc.getAttribute("number"));
    }
}
