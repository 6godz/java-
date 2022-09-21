package main.java.util;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtil {

    private static Connection ct;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Properties pro = new Properties();

    static {
        // 1.加载驱动
        try {
            pro.load(JDBCUtil.class.getResourceAsStream("/JDBCconfig.properties"));
            Class.forName(pro.getProperty("driverClassName"));
        } catch (Exception e) {
            System.out.println("数据库加载驱动出错");
        } // 反射加载驱动
    }

    // 2.封装一个获得连接的方法
    public static Connection getConnection() {
        // 2.建立与数据库的连接
        try {
            ct = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("username"),
                    pro.getProperty("password"));
        } catch (SQLException e) {
            System.out.println("数据库连接出错");
        }
        return ct;
    }

    // 封装一个增删改的方法
    public static int executeUpdate(String sql, Object... obj) {
        // 获得链接
        ct = getConnection();
        try {
            // 3.获得执行sql语句的对象
            ps = ct.prepareStatement(sql);
            // 处理占位符
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            // 增删改都是同一个方法
            int in = ps.executeUpdate();
            return in;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return 0;

    }

    // 封装一个查询的方法
    public static ResultSet executeQuery(String sql, Object... obj) {
        // 获得链接
        ct = getConnection();
        try {
            ps = ct.prepareStatement(sql);
            // 处理占位符
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 封装一个查询的方法，返回集合
    public static <T> List<T> executeQuery(String sql, Class<T> cl, Object... obj) {
        // 获得链接
        ct = getConnection();
        try {
            ps = ct.prepareStatement(sql);
            // 处理占位符
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            rs = ps.executeQuery();
            // 创建一个集合用来存放最终的数据
            List<T> list = new ArrayList<T>();
            Constructor<T> ct = cl.getDeclaredConstructor();
            while (rs.next()) {
                T t = ct.newInstance();
                t = selectObject(cl, t, rs);
                list.add(t);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return null;
    }

    // 封装一个关闭资源方法
    public static void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ct != null) {
            try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 封装一个关闭资源方法
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ct != null) {
            try {
                ct.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // 根据类上的注解获取表名
    public static String getTableName(Class<?> c) {
        TableName t = c.getAnnotation(TableName.class);
        return t.value();

    }

    // 创表
    public static int createTable(Class<?> class1) {
        // sql ：create table person(number int,name varchar(255));
        StringBuilder sql = new StringBuilder("create table if not EXISTS ");
        // 获得表名字
        sql.append(getTableName(class1)).append("(");
        // 获得所有字段的名字
        Field[] fields = class1.getDeclaredFields();
        for (Field f : fields) {
            Table ma = f.getDeclaredAnnotation(Table.class);
            if (!ma.type().toLowerCase().contains("date")) {
                sql.append(ma.columnName()).append(" ").append(ma.type()).append("(").append(ma.length()).append(") ")
                        .append(ma.checked()).append(",");
            } else {
                sql.append(ma.columnName()).append(" ").append(ma.type()).append(" ").append(ma.checked()).append(",");
            }
        }
        // 去掉最后一个逗号
        sql.deleteCharAt(sql.length() - 1).append(")");
        // 执行sql语句
        System.out.println(sql);
        return executeUpdate(sql.toString());
    }

    // 插入数据
    public static int insert(Object obj) {
        StringBuilder sql = new StringBuilder("insert into ");
        // 用反射获取class对象
        Class<? extends Object> cl = obj.getClass();
        // 获取类名字
        sql.append(getTableName(cl)).append("(");
        // 要获得成员变量
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            // 暴力反射
            f.setAccessible(true);
            Table ma = f.getDeclaredAnnotation(Table.class);
            try {
                if (f.get(obj) != null && !ma.checked().toLowerCase().contains("auto_increment")) { // 不为空不自动增长
                    sql.append(ma.columnName()).append(",");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 删除最后一个逗号
        sql.deleteCharAt(sql.length() - 1).append(") values(");
        // 循环获取变量的值
        for (Field f : fields) {
            // 暴力反射
            f.setAccessible(true);
            try {
                Table ma = f.getDeclaredAnnotation(Table.class);
                if (f.get(obj) != null && !ma.checked().toLowerCase().contains("auto_increment")) { // 不为空且不自动增长
                    Object ostr = f.get(obj); // 获取该字段的值
                    sql.append("'");
                    // 判断该字段类型是否是日期类型，是的话格式转换，否则正常添加
                    if (ma.type().toLowerCase().contains("date")) {
                        // 将时间转换成string类型
                        sql.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ostr));
                    } else {
                        sql.append(ostr);
                    }
                    sql.append("',");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 去掉最后一个逗号
        sql.deleteCharAt(sql.length() - 1).append(");");
        System.out.println(sql);
        try {
            return executeUpdate(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 根据主键更新某条数据，只需传入一个要对象，要修改哪个值就先改好再传进来
    public static int updateByPrimaryKey(Object pkVal, Object obj) {
        StringBuilder sql = new StringBuilder();
        // 得到class对象
        Class<? extends Object> cl1 = obj.getClass();

        // sql语句
        sql.append("update ").append(getTableName(cl1)).append(" set ");
        // 获取字段及值
        Field[] fields = cl1.getDeclaredFields();
        String primaryKey = "";
        for (Field f : fields) {
            f.setAccessible(true);
            // 获得注解
            Table a = f.getAnnotation(Table.class);
            // 找主键
            if (a.checked().toLowerCase().contains("primary key")) {
                primaryKey = a.columnName();
            }
            try {
                Object o = f.get(obj);
                // 调用者没有传入值或者自动增长的字段都不会被修改
                if (o != null && !o.equals(0) && !o.equals(0.0)
                        && !a.checked().toLowerCase().contains("auto_increment")) {
                    sql.append(a.columnName()).append("= '");
                    // 判断该字段类型是否是日期类型，是的话格式转换，否则正常添加
                    if (a.type().toLowerCase().contains("date")) {
                        // 将时间转换成string类型
                        sql.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o));
                    } else {
                        sql.append(o);
                    }
                    sql.append("' ,");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // bean类没有设置主键
        if (primaryKey.equals("")) {
            return 0;
        }
        // 删除最后一个逗号
        sql.delete(sql.length() - 1, sql.length());

        sql.append(" where ").append(primaryKey).append("= '").append(pkVal).append("';");
        System.out.println(sql);
        // 操作数据库
        return executeUpdate(sql.toString());

    }

    // 根据主键修改某条数据，修改的字段将根据调用者传入的参数来。
    public static int updateByPrimaryKey(Object pkVal, Object obj, boolean flag) {
        StringBuilder sql = new StringBuilder();
        // 得到class对象
        Class<? extends Object> cl1 = obj.getClass();
        // sql语句
        sql.append("update ").append(getTableName(cl1)).append(" set ");
        // 获取字段及值
        Field[] fields = cl1.getDeclaredFields();
        String primaryKey = "";
        for (Field f : fields) {
            f.setAccessible(true);
            // 获得注解
            Table a = f.getAnnotation(Table.class);
            // 如果该字段是主键，记录主键名字，并跳过该字段修改
            if (a.checked().toLowerCase().contains("primary key")) {
                primaryKey = a.columnName();
                continue;
            }
            try {
                // 如果该字段不是自动增长，则要被修改
                if (!a.checked().toLowerCase().contains("auto_increment")) {
                    Object o = f.get(obj);
                    sql.append(a.columnName()).append("= '");
                    if (a.type().toLowerCase().contains("date")) {
                        // 将时间转换成string类型
                        sql.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o));
                    } else {
                        sql.append(o);
                    }
                    sql.append("' ,");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // bean类没有设置主键
        if (primaryKey.equals("")) {
            return 0;
        }
        // 删除最后一个逗号
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" where ").append(primaryKey).append("= '").append(pkVal).append("';");
        // 操作数据库
        System.out.println(sql);
        return executeUpdate(sql.toString());
    }

    // 删除数据通过主键
    public static int deleteByPrimaryKey(Object pkVal, Class<?> cl) {
        StringBuilder sql = new StringBuilder("delete from ");
        Field[] fields = cl.getDeclaredFields();
        String primaryKey = "";
        // 找主键
        for (Field f : fields) {
            Table a = f.getAnnotation(Table.class);
            if (a.checked().toLowerCase().contains("primary key")) {
                primaryKey = a.columnName();
            }
        }
        // bean类没有主键则返回0
        if (primaryKey.equals("")) {
            return 0;
        }
        sql.append(getTableName(cl)).append(" where ").append(primaryKey).append("='" + pkVal + "';");
        return executeUpdate(sql.toString());
    }

    // 根据主键查询数据
    public static <T> T selectByPrimaryKey(Object pkVal, Class<T> cl) {
        try {
            // 获得class对象
            Constructor<T> ct = cl.getDeclaredConstructor();
            T t = ct.newInstance();
            StringBuilder sql = new StringBuilder("select * from ");

            Field[] fields = cl.getDeclaredFields();
            String primaryKey = "";
            // 找主键
            for (Field f : fields) {
                Table a = f.getAnnotation(Table.class);
                if (a.checked().toLowerCase().contains("primary key")) {
                    primaryKey = a.columnName();
                }
            }
            // bean类没有主键则返回0
            if (primaryKey.equals("")) {
                return null;
            }
            sql.append(getTableName(cl)).append(" where ").append(primaryKey).append("='").append(pkVal).append("';");
            // 去数据库查询
            return executeQuery(sql.toString(), cl).get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> selectByArrays(Class<T> c) {
        String sql = "select * from " + getTableName(c);
        try {
            // 去数据库查询得到集合
            return executeQuery(sql, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将ResultSet的结果通过反射与注解，封装到一个对象，并返回该对象
    private static <T> T selectObject(Class<T> cl, T t, ResultSet rs) throws Exception {
        // bean类的命名规则一定要遵循驼峰命名，同时不能第一个字母小写，第二个字母大写，否则封装失败
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Table a = f.getAnnotation(Table.class);
            String name = f.getName();
            // 把字段第一个字母变大写
            String methodName = "set" + name.replaceFirst("[a-z]", String.valueOf((char) (name.charAt(0) - 32)));
            Method m = cl.getMethod(methodName, f.getType());
            m.invoke(t, rs.getObject(a.columnName()));
        }
        return t;
    }
}