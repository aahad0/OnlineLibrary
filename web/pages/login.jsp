<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Library Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logincss.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="background">
        <div class="overlay">
            <div class="container">
                <div class="intro-section">
                    <h1>Welcome to CSE Online Library </h1>
                    <p>
                        This platform allows students to log in, select their semester, browse subjects, and view books available in the library.
                        Fast, reliable, and easy to use!
                    </p>
                </div>
                <div class="login-section">
                    <h2>Login to Your Account</h2>
                    <form method="post" action="${pageContext.request.contextPath}/login" class="login-form">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" name="username" id="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" required>
                        </div>
                        <c:if test="${not empty error}">
                            <p class="error-message">${error}</p>
                        </c:if>
                        <button type="submit">Login</button>
                    </form>
                    <p class="register-text">Don't have an account? <a href="reg">Register here</a></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
