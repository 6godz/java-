package main.java.bean;

import main.java.util.Table;
import main.java.util.TableName;

/**
 * @功能:
 * @作者:lgz
 */
@TableName("employee")
public class Employee {
    @Table(columnName = "number", type = "varchar", length = 20, checked = "not null primary key unique")
    private String number;

    @Table(columnName = "username", type = "varchar", length = 50, checked = "default null")
    private String username;

    @Table(columnName = "password", type = "varchar", length = 50, checked = "default null")
    private String password;

    @Table(columnName = "sex", type = "varchar", length = 50, checked = "default null")
    private String sex;

    @Table(columnName = "phone", type = "varchar", length = 11, checked = "default null unique")
    private String phone;

    @Table(columnName = "role", type = "int", length = 11, checked = "not null")
    private int role;

    @Table(columnName = "remark", type = "int", length = 1, checked = "default 1")
    private int remark;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public Employee() {
        super();
    }

    public Employee(String number, String username, String password, String sex, String phone, int role, int remark) {
        super();
        this.number = number;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.role = role;
        this.remark = remark;

    }

    @Override
    public String toString() {
        return "Employee [编号：" + number + ", 姓名：" + username + ", 性别：" + sex + ", 电话：" + phone + ", 职位："
                + (role == 1 ? "管理员" : role == 2 ? "收银员" : "采购员") + ", 状态：" + (remark == 1 ? "在职" : "离职") + "]";

    }

}
