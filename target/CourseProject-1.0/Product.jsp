<%@ page import="Model.Shop" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type='text/javascript' src='Validate.js'></script>
    <link rel='StyleSheet' type='text/css' href='main.css'/>
    <title>Product</title>
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

    <form action="searchProduct" method="post">
       <div> <input class="textbox" type="text" name="title" %/>
       <input class="button" type="submit" value="Поиск"/><div/>
        <c:if test="${listProduct.size() == 0}">
            <br/>
            Поиск не дал результатов
        </c:if>
        <c:if test="${listProduct.size() != 0}">
            <table class="table" border="10" cellpadding="10">
                <caption><h2>
                    Товары</h2></caption>
                <tr>
                    <th>Артикул</th>
                    <th>Наименование</th>
                    <th>Цена</th>
                    <th>Количество</th>
                    <th colspan="2">Действие</th>
                </tr>
                <%
                    List<Product> listProduct = (List<Product>) session.getAttribute("listProduct");
                    for (Product product : listProduct) {
                %>
                <tr>
                    <td><%=product.getBarcode()%>
                    </td>
                    <td><a class="link" href="/whereProduct?titleProduct=<%=product.getTitle()%>"><%=product.getTitle()%>
                    </a></td>
                    <td><%=product.getPrice()%>
                    </td>
                    <td><%=product.getCount()%>
                    </td>
                    <td> <a class="link" href="/editProduct?id=<%=product.getBarcode()%>">Изменить</a>
                        <a class="link" href="/deleteProduct?id=<%=product.getBarcode()%>">Удалить</a></td>
                            <%}%>
                <tr/>
            </table>
        </c:if>
        <h2>
            <c:if test="${listProduct.size() == 0}">
            </c:if>
            <c:if test="${listProduct.size() != 0}">
                <a class="link" id="linkNew" href="/newProduct">Добавить товар</a>
            </c:if>
        </h2>
    <form/>
    <div  id="error" error_message="<c:out value='${error}' />"></div>
</div>
    </form>
</div>
</body>
<script>
    var error = document.getElementById("error").getAttribute('error_message');
    if (error)
        alert(error);
</script>
</html>