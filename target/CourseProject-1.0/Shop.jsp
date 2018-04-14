<%@ page import="Model.Shop" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
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
<body>
<center>
    <!--h1>Магазин</h1-->

</center>
<div align="center">
    <table class="table" border="10"  cellpadding="10">
        <caption>
            <h2>Магазины</h2>
        </caption>
        <tr>
            <th>ФИО директора</th>
            <th>Телефон</th>
            <th>Адрес</th>
            <th colspan="2">Действие</th>
        </tr>
        <tr></tr>
        <c:forEach var="shop" items="${listShop}">
            <tr>
                <td><c:out value="${shop.shopId}"/></td>
                <td><c:out value="${shop.directorSurname}"/></td>
                <td><c:out value="${shop.directorName}"/></td>
                <td><c:out value="${shop.directorMiddlename}"/></td>
                <td><c:out value="${shop.telephone}"/></td>
                <td><c:out value="${shop.address}"/></td>
                <td>
                    <a href="/edit?shopId=<c:out value='${shop.shopId}' />" class="s1">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?shopId=<c:out value='${shop.shopId}' />" class="s1">Удалить</a>
                </td>
            </tr>
        </c:forEach>

</table>
    <h2>
        <a class="top-menu" id="menu-main" href="/newShop">Добавить магазин</a>
    </h2>
</div>
</body>
</html>