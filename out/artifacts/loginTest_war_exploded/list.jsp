<%--
  Created by IntelliJ IDEA.
  User: s1xgod
  Date: 2022/9/20
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.1.min.js"></script>
    <style>
        a {
            margin-left: 2px;
            margin-right: 2px;
            text-decoration: none;
            color: black;
        }

        table {
            border: 1px solid black;
            border-collapse: collapse;
        }

        td {
            width: 150px;
            border: 1px solid black;
            text-align: center;
        }

        th {
            border: 1px solid black;
            text-align: center;
        }

    </style>
</head>
<body>
<div>
    <table>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>手机</th>
            <th>职位</th>
        </tr>
        <c:forEach items="${requestScope.es}" var="ee">
            <tr>
                <td>${ee.number}</td>
                <td>${ee.username}</td>
                <td>${ee.sex}</td>
                <td>${ee.phone}</td>
                <td>${ee.role==1?"管理员":(ee.role==2?"收银员":"采购员")}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <button id="first">首页</button>
    <button id="front">上一页</button>
    <span id="pageNumber">

    </span>
    <button id="next">下一页</button>
    <button id="last">尾页</button>
    <input type="number" id="input" min="1" minlength="1" style="width: 50px">
    <button id="submit">确定</button>
    共<span id="count">${count}</span>条

</div>

</body>
<script>
    var page = ${page};
    $("#input").val(page);

    $("#first").click(function () {
        window.location = "list?page=1&limit=10";
    })
    $("#front").click(function () {
        if (page > 1) {
            window.location = "list?page=" + (page - 1) + "&limit=10";
        }
    })
    var num = $("#count").text();
    var str = "<span id=\"pageNumber\">";
    var startPage = 1;
    if (page > 10) {
        startPage = page - 9;
    }

    for (var i = startPage; i <= Math.ceil(num / 10) && i < startPage + 10; i++) {
        str += "<a href='list?page=" + i + "&limit=10'>" + i;
    }
    $("#last").click(function () {
        window.location = "list?page=" + Math.ceil(num / 10) + "&limit=10";
    })
    $("#next").click(function () {
        if (page < Math.ceil(num / 10)) {
            window.location = "list?page=" + (page + 1) + "&limit=10";
        }
    })
    $("#pageNumber").html(str + "</span>")

    var as = $("a");
    for (var i = 0; i < as.length; i++) {
        console.log(as.eq(i) + "   s " + i)
        if (as.eq(i).text() == page) {

            as.eq(i).css("color", "red")
        } else {
            as.eq(i).css("color", "black");
        }
    }

    $("#submit").click(function () {
        if ($("#input").val() == "") {
            return;
        }
        if ($("#input").val() < 1) {
            window.location = "list?page=1&limit=10";
        } else if ($("#input").val() > Math.ceil(num / 10)) {
            window.location = "list?page=" + Math.ceil(num / 10) + "&limit=10";
        } else {
            window.location = "list?page=" + $("#input").val() + "&limit=10";
        }
    })
</script>
</html>


