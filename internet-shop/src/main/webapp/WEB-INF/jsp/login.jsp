<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>

<body>
<h1>Login Form</h1>
<form action="shop" method="post">
    <table>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
<p>
    <button type="button" onclick="location.href='http://localhost:8080/registration'">Register</button>
</p>
</body>

</html>