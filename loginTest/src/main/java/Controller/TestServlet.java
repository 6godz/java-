package main.java.Controller;

import main.java.Service.EmployeeService;
import main.java.bean.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    private EmployeeService es = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        System.out.println(req.getMethod());
//        System.out.println(req.getQueryString());
//        System.out.println(req.getRequestURI());
//        System.out.println(req.getRequestURL());
//        System.out.println(req.getServletPath());
//        System.out.println(req.getServerName());
//        System.out.println(req.getServerPort());
//        System.out.println(req.getRemoteHost());
//        System.out.println(req.getRemoteUser());
//        System.out.println(req.getRemoteAddr());
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();

            System.out.println(name + ":" + req.getParameter(name));
        }
        System.out.println("req.getContextPath():" + req.getContextPath());
    }
}
