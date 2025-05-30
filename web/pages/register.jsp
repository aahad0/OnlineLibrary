<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/registercss.css" />
</head>
<body>
    <div class="overlay">
        <div class="container">
            <h2>Register</h2>
            <form method="post" action="${pageContext.request.contextPath}/register">
                <label>Username:</label>
                <input type="text" name="username" required />

                <label>Password:</label>
                <input type="password" name="password" required />

                <label>Email:</label>
                <input type="email" name="email" />

                <input type="submit" value="Register" />
            </form>

            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>

            <p>Already have an account? <a href="lgin">Login here</a></p>
        </div>
    </div>
</body>
</html>
