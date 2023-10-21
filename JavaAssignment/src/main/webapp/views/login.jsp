<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;600&display=swap');

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        text-decoration: none;
        font-family: 'Poppins', sans-serif;
    }

    .app {
        height: 100vh;
        width: 100vw;
        max-width: 100vw;
        max-height: 100vh;
        background-color: white;
    }

    .header {
        width: 100%;
        height: 50px;
        padding: 1em;
        background-color: rgb(226, 226, 226);
    }

    .title .page-title {
        font-weight: 600;
    }

    .auth-container {
        height: 250px;
        width: 350px;
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: rgb(245, 243, 243);
        border-radius: 20px;
        padding: 20px;
    }

    .form-container {
        width: inherit;
        height: inherit;
        padding: 15px;
    }

    .form-input {
        display: block;
        width: 80%;
        height: 30px;
        border-radius: 5px;
        border: none;
        padding: 5px;
        margin-top: 15px;
        margin-bottom: 15px;
    }

    .form-button {
        background-color: aqua;
        color: white;
        padding: 5px;
        border: none;
        border-radius: 5px;
    }

    .error-message {
        color: red;
        font-weight: 600;
        margin-top: 10px;
    }
</style>
<body>
<div class="app">
    <div class="header">
        <div class="title"><span class="page-title">Login Page</span></div>
    </div>
    <div class="auth-container">
        <div class="form-container">
            <form action="/Customer-web/login" method="POST" id="login_form">
                <input class="form-input" type="text" id="login_id" name="username" placeholder="Username">
                <input class="form-input" type="password" id="passwd" name="password" placeholder="Password">
                <button class="form-button" type="submit" form="login_form" value="submit">Submit</button>
            </form>
            <div class="error-message">
                <c:if test="${not empty errorMessage}">
                    ${errorMessage}
                </c:if>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${not empty customerList}">
            <script type="text/javascript">
                window.location.href = 'customer-list.jsp';
            </script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript">
                window.location.href = 'login.jsp';
            </script>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
