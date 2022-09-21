<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="main.java.util.DownloadUtil" %><%--
  Created by IntelliJ IDEA.
  User: s1xgod
  Date: 2022/9/19
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.1.min.js"></script>
</head>

<body>
<%
    List<String> filenames = (List<String>) request.getAttribute("filenames");
    for (String f : filenames) {
        out.write("<div><button ><a href='Delete?filename=" + f + "'>删除</a></button>");
        out.write("<span><a href='Download2?filename=" + f + "'>" + f + "</a></span></div>");
    }
%>


</body>

</html>
