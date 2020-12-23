<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12/11/2020
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div>
    <sec:authorize access="!isAuthenticated()">
        <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Login</a></p>
        <p><a class="btn btn-lg btn-success" href="<c:url value="/registration" />"
              role="button">Registration</a></p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p>Your username: <sec:authentication property="principal.username"/></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
    </sec:authorize>
<div>

</div>
    <h2>List of Products</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.price}"/></td>
                <form action="shop/add" method="post">
                    <td>
                        <input type="hidden" name="userId" c:out value="${userId}"/>
                        <input type="hidden" name="productId" c:out value="${product.id}"/>
                        <input type="hidden" name="price" c:out value="${product.price}"/>
                        <button type="submit">Add to basket</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>


   <sec:authorize access="isAuthenticated()">
       <div>
           <h2>Your basket</h2>
           <table border="1" cellpadding="5">
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
                               <input type="hidden" name="productId" c:out value="${product.id}"/>
                               <button type="submit">Delete</button>
                           </form>
                       </td>
                   </tr>
               </c:forEach>
           </table>
       </div>
   </sec:authorize>
</div>
</body>
</html>
