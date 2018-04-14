<%@ page import="Model.Section" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type='text/javascript' src='Validate.js'></script>
    <link rel='StyleSheet' type='text/css' href='main.css' />
    <title>Shop</title>
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
<body onload="DigitSection()">
<center>
    <!--h1>Магазин</h1-->

</center>
<div align="center">
    <table class="table" border="10"  cellpadding="10">
        <caption><h2>Отделы</h2></caption>

        <tr>
        <tr><tr/>

        <th>Артикул</th>
        <th>Телефон</th>
        <th>Название отдела</th>
        <th colspan="2">Действие</th>

        </tr>
        <c:forEach var="section" items="${listSection}">
            <tr>
                <td><c:out value="${section.sectionId}"/></td>
                <td><c:out value="${section.telephone}"/></td>
                <td><c:out value="${section.title}"/></td>
                <td>
                    <a href="/edit?sectionId=<c:out value='${section.sectionId}' />" class="s1">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?sectionId=<c:out value='${section.sectionId}' />" class="s1">Удалить</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <h2>
        <a class="link" id="linkNew" href="/newSection">Добавить отдел</a>
        &nbsp;&nbsp;&nbsp;
    </h2>
</div>
</body>
</html>