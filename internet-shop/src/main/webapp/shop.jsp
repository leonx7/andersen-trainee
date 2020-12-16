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
                <form action="products/add_to_basket" method="post">
                    <td>
                        <label>Quantity: </label>
                        <input type="number" name="quantity" value="1" size="5">
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
            <th>Price</th>
            <th>Action</th>
        </tr>
        <c:forEach var="product" items="${productsFromBasket}">
            <tr>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.quantity}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td>
                    <a href="/deleteFromBasket?id=<c:out value='${book.id}' />">
                        <button>Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><b>Total amount:</b></td>
            <td colspan="2"><c:out value="${product.price}"/></td>
        </tr>
    </table>
</div>
</body>
</html>
