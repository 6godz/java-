package main.java.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DownloadUtil {
    private static String loc = "/WEB-INF";

    public static void getFileNames(HttpServletRequest request, HttpServletResponse response, ServletContext sc) throws ServletException, IOException {
        System.out.println(sc.getRealPath(loc));
        File f = new File(sc.getRealPath(loc));
        List<String> list = new ArrayList<>();
        getFileAll(list, f, "");
        System.out.println(list.toString());
        request.setAttribute("filenames", list);
        request.getRequestDispatcher("download.jsp").forward(request, response);
    }


    public static void getFileAll(List<String> list, File file, String loc) {
        // 获得这个文件夹中的所有文件

        File[] listFiles = file.listFiles();
        try {
            for (File f : listFiles) {
                // 判断file是否是文件夹
                if (f.isDirectory()) {
                    getFileAll(list, f, loc + "/" + f.getName());
                } else {
                    // 输出文件的名字
                    list.add(loc + "/" + f.getName());
                }
            }
        } catch (Exception e) {

        }
    }


    public static void downfile(HttpServletRequest request, HttpServletResponse response, ServletContext sc) throws IOException {
        //要获取要下载的文件名字
        String filename = request.getParameter("filename");
        System.out.println("要下载的文件名字是：" + filename);

        //得到文件的路径
        String realPath = sc.getRealPath(loc);
        System.out.println(realPath);

        //得到一个输入流
        FileInputStream in = new FileInputStream(realPath + "/" + filename.substring(1));
        //设置响应头：意思就是要浏览器器以下载的形式打开
        filename = new String(filename.getBytes(), "iso8859-1");
        response.setHeader("content-disposition", "attachment;filename=" + filename.substring(filename.lastIndexOf("/") + 1));
        //获取一个输出流
        OutputStream out = response.getOutputStream();
        byte[] bu = new byte[1024 * 4];
        int len = 0;
        while ((len = in.read(bu)) != -1) {
            out.write(bu, 0, len);
        }

        //关闭资源
        in.close();
        out.close();
    }

    public static void deleteFile(HttpServletRequest request, HttpServletResponse response, ServletContext sc) throws ServletException, IOException {
        // 要删除的文件名字
        String filename = request.getParameter("filename");
        System.out.println(filename);
        String realPath = sc.getRealPath(loc);
        System.out.println(realPath);
        File f = new File(realPath + filename);
        boolean delete = f.delete();
        System.out.println(delete);
        System.out.println(f.getName());
        request.getRequestDispatcher("download").forward(request, response);
    }

}
