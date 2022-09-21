package main.java.Dao;

import main.java.bean.Employee;
import main.java.util.JDBCUtil;

import java.util.List;

public class EmployeeDao {
    public List<Employee> getEmployeeByName(String name) {
        List<Employee> es = JDBCUtil.executeQuery("select * from employee where username = ?", Employee.class, name);
        return es;
    }

    public int addEmployee(Employee e) {
        int insert = JDBCUtil.insert(e);
        return insert;
    }
}
