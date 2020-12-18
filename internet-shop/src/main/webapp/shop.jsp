<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12/11/2020
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<div>
    <table border="1" cellpadding="5">
        <caption><h2>List of Products</h2></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><c:out value="${product.productId}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.price}"/></td>
                <form action="shop/add" method="post">
                    <td>
                        <input type="hidden" name="userId" c:out value="${userId}"/>
                        <input type="hidden" name="productId" c:out value="${product.productId}"/>
                        <button type="submit">Add to basket</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <table border="1" cellpadding="5">
        <caption><h2>Your basket</h2></caption>
        <tr>
            <th>Name</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        <c:forEach var="product" items="${productsFromBasket}">
            <tr>
                <td><c:out value="${product.name}"/></td>
                <td><input type="number" name="quantity" min="1" value="1" style="width: 50px"></td>
                <td>
                    <form action="shop/delete" method="post">
                        <input type="hidden" name="productId" c:out value="${product.productId}"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
