package main.java.Controller;

import main.java.Service.EmployeeService;
import main.java.bean.Employee;
import main.java.util.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private EmployeeService es = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String sex = req.getParameter("sex");
//        String phone = req.getParameter("phone");
//        int role = Integer.parseInt(req.getParameter("role"));
//        String number = req.getParameter("number");
//        String res = "";
//        try {
//            Class employee = Class.forName("main.java.bean.Employee");
//            Employee e = (Employee) employee.getConstructors()[1].newInstance(number, username, password, sex, phone, role, 1);
//            res = es.register(e);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Employee emp = BeanUtil.getBean(Employee.class, req, resp);
        resp.getWriter().write(es.register(emp));
    }
}
