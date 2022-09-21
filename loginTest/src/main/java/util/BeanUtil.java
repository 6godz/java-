package main.java.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class BeanUtil {
    public static <T> T getBean(Class<T> c, HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 创建T类的构造器
            Constructor<T> constructor = c.getDeclaredConstructor();
            // 创建T类的对象
            T t = constructor.newInstance();
            // 获取所有属性
            Field[] fields = c.getDeclaredFields();
            // 获取req的所有参数
            Map<String, String[]> map = req.getParameterMap();
            // 遍历bean类的所有属性
            for (Field f : fields) {
                f.setAccessible(true);
                String[] vals = map.get(f.getName());
                if (vals == null) {
                    continue;
                }
                if (vals.length == 1) {
                    // 该属性是整型
                    if (f.getType() == Integer.class || f.getType() == int.class) {
                        // 整型的输入框没有填内容
                        if (vals[0].equals("")) {
                            continue;
                        }
                        f.set(t, Integer.parseInt(vals[0]));
                    } else { // 是字符串
                        f.set(t, vals[0]);
                    }
                } else {
                    f.set(t, Arrays.toString(vals));
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
