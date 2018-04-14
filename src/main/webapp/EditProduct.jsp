<%@ page import="Model.Product" %>
<%@ page import="Model.Shop" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Section" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type='text/javascript' src='Validate.js'></script>
    <link rel='StyleSheet' type='text/css' href='main.css' />
</head>
<header >
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
    <c:if test="${product != null}">
    <form name ="form1" action="updateProduct" method="post">
        </c:if>
        <c:if test="${product == null}">
        <form action="insertProduct" method="post">
            </c:if>
            <table class="table" id="editTable" border="10" cellpadding="10">
                <caption>
                    <h2>
                        <c:if test="${product != null}">
                            Изменить товар
                            <br/>
                        </c:if>
                        <c:if test="${product == null}">
                            Новый товар
                            <br/>
                        </c:if>
                    </h2>
                </caption>
                <!--%
                    String id = request.getParameter("id").toString();
                %>
                <input type="hidden" name="id"
                       value='<!--%=id%>'
                  /-->

               <tr>
                <th>Артикул: </th>
                <td>
                    <input required type="text" name="barcode" size="45" onkeypress="return isNumberKey(event)"
                           value='${product.barcode}'
                    />
                </td> </tr>
                <tr>
                    <th>Наименование: </th>
                    <td>
                        <input required type="text" name="title" size="45"
                               value="<c:out value='${product.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Цена: </th>
                    <td>
                        <input required type="text" name="price" size="45" onkeypress="return isNumberKey(event)"
                               value="<c:out value='${product.price}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Количество: </th>
                    <td>
                        <input required type="text" name="count" size="45" onkeypress="return isNumberKey(event)"
                               value="<c:out value='${product.count}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Код отдела: </th>
                    <td>
                        <select class ="list" name ="id_section_selection">

                            <%
                                Map map = (HashMap<String,Section>)session.getAttribute("listSection");
                                Set<Map.Entry<Section, String>> set = map.entrySet();
                                for (Map.Entry<Section,String> entry : set) {
                            %>
                            <option  name ="id_section" value="<%=entry.getKey().getSectionId()%>">
                                <%=entry.getKey().getSectionId()%></option>
                            <%}%>
                        </select>
                        <input type="hidden" id="product_id_section" value='${product.id_section}' />

                        <script>
                            document.getElementsByTagName("id_section_selection").value=document.getElementById("product_id_section").value;
                        </script>
                    </td>
                </tr>s
                <tr>
                    <td colspan="2" align="center">
                        <input class="button" required type="submit" value="Сохранить" />
                    </td>
                </tr>
            </table>
            <h2>
                <a class="link" id="linkNew" href="/listProduct">назад</a>
            </h2>
        </form>
</div>
</body>
</html>