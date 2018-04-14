<%@ page import="Model.Section" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
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

<div align="center">
    <table class="table" border="10"  cellpadding="10">
        <caption><h2>Отделы</h2></caption>
        <br/>
        <tr>

            <th>Название</th>
            <th>Телефон</th>
            <th>Адрес магазина</th>


        </tr>
        <tr></tr>
        <%
            Map map = (HashMap<String,Section>)session.getAttribute("listSectionShop");
            Set<Map.Entry<Section, String>> set = map.entrySet();
            for (Map.Entry<Section,String> entry : set) {
        %>

        <tr>
            <td><%=entry.getKey().getTitle()%></td>
            <td><%=entry.getKey().getTelephone()%></td>
            <td><%=entry.getValue()%></td>

                <%}%>

    </table>
    <h2>
        <!--a href="/newShop">Добавить магазин</a-->
        &nbsp;&nbsp;&nbsp;
        <a class="link" id="linkNew" href="/listProduct">Назад</a>

    </h2>
</div>
</body>
</html>