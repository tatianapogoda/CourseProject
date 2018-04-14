<%@ page import="Model.Section" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="DAO.ShopDAO" %>
<%@ page import="Model.Shop" %>
<%@ page import="Model.Manager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type='text/javascript' src='Validate.js'></script>
    <link rel='StyleSheet' type='text/css' href='main.css' />
    <!--title>ShopAdd</title-->
</head >
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
<body >
<div align="center">
    <c:if test="${section != null}">
    <form  action="updateSection" method="post">
        </c:if>
        <c:if test="${section == null}">
        <form   action="insertSection" method="post">
            </c:if>
            <table class="table" id="editTable" border="10" cellpadding="10">
                <caption>
                    <h2>
                        <c:if test="${section != null}">
                            Изменить отдел

                        </c:if>
                        <c:if test="${section == null}">
                            Новый отдел

                        </c:if>
                    </h2>
                </caption>
                <input type="hidden" name="id_section"
                       value="<c:out value='${section.sectionId}' />"
                />
                <tr>
                    <th>Название: </th>
                    <td>
                        <input required type="text" name="title" size="45"
                               value="<c:out value='${section.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Телефон: </th>
                    <td>
                        <input required  type="text" name="telephone" size="45"
                               value="<c:out value='${section.telephone}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Код магазина: </th>
                    <td>
                            <select class ="list" name ="id_shop_selection">

                                <%
                                    List<Shop> listShop = (List<Shop>) session.getAttribute("listShop");
                                    for(int i=0;i<listShop.size();i++){
                                %>
                                <option  name ="id_shop"
                                         value="<%=listShop.get(i).getShopId()%>" ><%=listShop.get(i).getShopId()%></option>
                                <%}%>
                            </select>
                        <input type="hidden" id="section_id_shop" value='${section.shopId}' />

                        <script>
                            document.getElementsByTagName('id_shop_selection').value=document.getElementById('section_id_shop').value;
                        </script>

                    </td>
                </tr>
                <tr>
                    <th>Код менеджера: </th>
                    <td>
                        <select class="list"  name="id_manager_selection">

                            <%
                                List<Manager> listManager = (List<Manager>) session.getAttribute("listManager");
                                for(int i=0;i<listManager.size();i++){
                            %>
                            <option  name ="id_manager"
                                     value="<%=listManager.get(i).getManagerId()%>" >
                                <%=listManager.get(i).getManagerId()%></option>
                            <%}%>
                        </select>
                        <input type="hidden" id="section_id_manager" value='${section.managerId}' />

                        <script>
                            document.getElementsByTagName('id_manager_selection').value=document.getElementById('section_id_manager').value;
                        </script>

                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input class="button" type="submit" value="Сохранить" />
                    </td>
                </tr>
            </table>
            <h2>

                <a class="link" id="linkNew" href="/listSection">Назад к списку</a>

            </h2>
        </form>
</div>
</body>
</html>