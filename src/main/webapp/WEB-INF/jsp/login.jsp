<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Paint</title>
</head>
<body>
    <h2>Login:</h2>
    <form method = "post" action = "/login">
        <p>Usuari:</p>
        <input type = "text" name = "user">
        <p>Password:</p>
        <input type = "password" name = "password">
        <br>
        <input type = "submit">
    </form>
    <div>
        <c:if test = "${not empty message}">
            ${message}
        </c:if>
    </div>
</body>
</html>