package main.java.Service;

import main.java.Dao.EmployeeDao;
import main.java.bean.Employee;

import java.util.List;


public class EmployeeService {
    private EmployeeDao ed = new EmployeeDao();

    public String login(String name, String password) {
        List<Employee> es = ed.getEmployeeByName(name);
        if (es != null && es.size() != 0) {
            if (es.get(0).getPassword().equals(password)) {
                return "登录成功！ " + es.get(0).toString();
            } else {
                return "密码错误";
            }
        } else {
            return "用户不存在";
        }
    }

    public String register(Employee e) {
        int i = ed.addEmployee(e);
        if (i == 1) {
            return "添加成功！" + e.toString();
        } else {
            return "添加失败！";
        }
    }
}
