<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type='text/javascript' src='Validate.js'></script>
    <link rel='StyleSheet' type='text/css' href='main.css' />
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
    <c:if test="${manager != null}">
    <form action="updateManager" method="post">
        </c:if>
        <c:if test="${manager == null}">
        <form action="insertManager" method="post">
            </c:if>
            <table class="table" id="editTable" border="10" cellpadding="10">
                <caption>
                    <h2>
                        <c:if test="${manager != null}">
                            Изменить данные о менеджере
                            <br/>
                        </c:if>

                        <c:if test="${manager == null}">
                            Добавить нового менеджера
                            <br/>
                        </c:if>
                    </h2>
                </caption>
                <input required type="hidden" name="id_manager"
                           value="<c:out value='${manager.managerId}' />"
                    />
                <tr>
                    <th>Имя: </th>
                    <td>
                        <input required  type="text" name="name" size="45"
                               value="<c:out value='${manager.managerName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Фамилия: </th>
                    <td>
                        <input required  type="text" name="surname" size="45"
                               value="<c:out value='${manager.managerSurname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Отчество: </th>
                    <td>
                        <input required type="text" name="middlename" size="45"
                               value="<c:out value='${manager.managerMiddlename}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Телефон: </th>
                    <td>
                        <input required type="text" name="telephone" size="45 "
                               value="<c:out value='${manager.telephone}' />"
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

                <a class="link" id="linkNew" href="/listManager">Назад к списку</a>

            </h2>
        </form>
</div>
</body>
</html>