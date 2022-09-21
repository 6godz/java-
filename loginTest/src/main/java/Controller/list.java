package main.java.Controller;

import main.java.bean.Employee;
import main.java.util.BeanUtil;
import main.java.util.JDBCUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// 获取数据库的数据，并分页显示到前端
@WebServlet("/list")
public class list extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        System.out.println("dopost");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        if (page == null) {
            page = "1";
        }
        String limit = req.getParameter("limit");
        if (limit == null) {
            limit = "10";
        }
        int page2 = (Integer.parseInt(page) - 1) * Integer.parseInt(limit); // 转换为sql语句用的参数，表示查询从第几条开始
        System.out.println("page2:" + page2);
        req.setAttribute("page", page);
        ResultSet rs = JDBCUtil.executeQuery("select count(*) count from employee");
        try {
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("count:" + count);
                List<Employee> es = JDBCUtil.executeQuery("select * from employee limit " + page2 + "," + limit, Employee.class);
                System.out.println(es.toString());
                req.setAttribute("es", es);
                req.setAttribute("count", count);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close();
        }
        req.setAttribute("flag", 1);
        req.getRequestDispatcher("list.jsp").

                forward(req, resp);

    }
}
