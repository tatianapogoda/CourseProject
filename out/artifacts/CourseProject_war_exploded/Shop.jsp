<%@ page import="Model.Shop" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<center>
    <h1>Магазин</h1>
    <h2>
        <a href="/new">Добавить магазин</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">Весь список</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Магазины</h2></caption>
        <tr>
            <th>ID</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Middlename</th>
            <th>Telephone</th>
            <th>Address</th>
        </tr>
        <%
            List<Shop> listShop = (List<Shop>) session.getAttribute("listShop");

            for (Shop shop : listShop) {


        %>

        <tr>
            <td><%=shop.getShopId()%></td>
            <td><c:out value="${shop.Surname}"/></td>
            <td><c:out value="${shop.Name}"/></td>
            <td><c:out value="${shop.Middlename}"/></td>
            <td><c:out value="${shop.Telephone}"/></td>
            <td><c:out value="${shop.Address}"/></td>
            <td>
                <a href="/edit?id=<c:out value='${shop.id}' />">Изменить</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/delete?id=<c:out value='${shop.id}' />">Удалить</a>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>