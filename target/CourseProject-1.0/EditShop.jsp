<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type='text/javascript' src='Validate.js'></script>
    <link rel='StyleSheet' type='text/css' href='main.css' />
    <!--title>ShopAdd</title-->
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
    <!--h1>Shop</h1-->

</center>
<div align="center">
    <c:if test="${shop != null}">
    <form action="updateShop" method="post">
        </c:if>
        <c:if test="${shop == null}">
        <form action="insertShop" method="post">
            </c:if>
            <table class="table" id="editTable" border="10" cellpadding="10">
                <caption>
                    <h2>
                        <c:if test="${shop != null}">
                           Изменить магазин
                            <br/>
                        </c:if>
                        <c:if test="${shop == null}">
                           Новый магазин
                            <br/>
                        </c:if>
                    </h2>
                </caption>
                    <input type="hidden" name="id_shop"
                           value="<c:out value='${shop.shopId}' />"
                    />
                <tr>
                    <th>Имя: </th>
                    <td>
                        <input required type="text" name="name" size="45"
                               value="<c:out value='${shop.directorName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Фамилия: </th>
                    <td>
                        <input required type="text" name="surname" size="45"
                               value="<c:out value='${shop.directorSurname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Отчество: </th>
                    <td>
                        <input required type="text" name="middlename" size="45"
                               value="<c:out value='${shop.directorMiddlename}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Телефон: </th>
                    <td>
                        <input required  type="text" name="telephone" size="45"
                               value="<c:out value='${shop.telephone}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Адрес: </th>
                    <td>
                    <input required type="text" name="address" size="45"
                           value="<c:out value='${shop.address}' />"
                    />
                </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input class="button" type="submit" value="Сохранить" />
                    </td>
                </tr>
            </table>
                <h2>
                    <!--a href="/newShop">Добавить магазин</a-->
                    &nbsp;&nbsp;&nbsp;
                    <a class="link" id="linkNew" href="/listShop">Назад к списку</a>

                </h2>
        </form>
</div>
</body>
</html>