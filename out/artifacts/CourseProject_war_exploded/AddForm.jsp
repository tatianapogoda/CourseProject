<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ShopAdd</title>
</head>
<body>
<center>
    <h1>Shop</h1>
    <h2>
        <a href="/new">Добавить магазин</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">Все магазины</a>

    </h2>
</center>
<div align="center">
    <c:if test="${book != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${shop != null}">
                            Edit Shop
                        </c:if>
                        <c:if test="${shop == null}">
                           Новый магазин
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${shop != null}">
                    <input type="hidden" name="id_shop" value="<c:out value='${shop.id}' />" />
                </c:if>
                <tr>
                    <th>Имя: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${shop.name_director}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Фамилия: </th>
                    <td>
                        <input type="text" name="surname" size="45"
                               value="<c:out value='${shop.surname_director}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Отчество: </th>
                    <td>
                        <input type="text" name="middlename" size="45"
                               value="<c:out value='${shop.middlename_director}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Телефон: </th>
                    <td>
                        <input type="text" name="telephone" size="45"
                               value="<c:out value='${shop.telephone}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Адрес: </th>
                    <td>
                        <input type="text" name="address" size="45"
                               value="<c:out value='${shop.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>