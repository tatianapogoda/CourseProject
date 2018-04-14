
<%@ page import="java.util.List" %>
<%@ page import="Model.Manager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel='StyleSheet' type='text/css' href='main.css' />
    <title>Сотрудники</title>
</head>
<header>
    <div class="container">
        <div class="logo-box">
            <!--a href="/"-->
            <img src="logo.png">
            <!--/a-->
        </div>
        <nav>
            <ul>
                <li><a href="/listShop">Магазины</a></li>
                <li><a href="/listManager">Сотрудники</a></li>
                <li><a href="/listSection">Отделы</a></li>
                <li><a href="/listProduct">Товары</a></li>

            </ul>
        </nav>
    </div>
</header>
<body>
<center>
    <!--h1>Магазин</h1-->

</center>
<div align="center">
    <table class="table" border="10"  cellpadding="10">
        <caption><h2>Менеджеры</h2></caption>

        <tr>
            <!--th>id</th-->
            <th>ФИО сотрудника</th>
            <th>Телефон</th>
            <th colspan="2">Действие</th>

        </tr>
        <tr></tr>
        <%
            List<Manager> listManager = (List<Manager>) session.getAttribute("listManager");

            for (Manager manager : listManager) {


        %>

        <tr>
            <!--td><%=manager.getManagerId()%> </td-->
            <td><%=manager.getSurname()%> <%=manager.getName()%> <%=manager.getMiddlename()%></td>
            <td><%=manager.getTelephone()%></td>

            <td>
                <a class="link" href="/editManager?id=<%=manager.getManagerId()%>">Изменить</a>
            </td>
            <td>
                <a class="link" href="/deleteManager?id=<%=manager.getManagerId()%>">Удалить</a>
            </td>

                <%}%>
    </table>
    <h2>
        <a class="link" id="linkNew" href="/newManager">Добавить сотрудника</a>
    </h2>
</div>
</body>
</html>