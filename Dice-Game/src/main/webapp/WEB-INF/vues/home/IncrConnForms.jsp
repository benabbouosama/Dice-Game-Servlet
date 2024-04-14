<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bo.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <link href="${pageContext.request.contextPath}/style/InscrConn.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/style/InscrConn.js"></script>
</head>
<body>
<h2>Dice Game</h2>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form class="form-container" action="InscriptionServlet" method="post">
            <label for="nom">Name :</label><br>
            <input type="text" id="nom" name="nom" required><br><br>

            <label for="prenom">Firstname :</label><br>
            <input type="text" id="prenom" name="prenom" required><br><br>

            <label for="login">Login :</label><br>
            <input type="text" id="loginInscr" name="loginInscr" required><br><br>

            <label for="password">Password :</label><br>
            <input type="password" id="passwordInscr" name="passwordInscr" required><br><br>

            <input type="submit" value="Sign up">

            <br>
            <div>
                <ul>
                    <c:forEach items="${requestScope.messages}" var="msg">
                        <c:choose>
                            <c:when test="${msg.type == Message.WARN}">
                                <li style="color: orange">${msg.text}</li>
                            </c:when>
                            <c:when test="${msg.type == Message.INFO}">
                                <li style="color: green">${msg.text}</li>
                            </c:when>
                            <c:when test="${msg.type == Message.ERROR}">
                                <li style="color: red">${msg.text}</li>
                            </c:when>
                            <c:otherwise>
                                <li>${msg.text}</li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form class="form-container" action="ConnexionServlet" method="post">
            <label for="login">Login :</label>
            <input type="text" id="login" name="login" required>

            <label for="password">Password :</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Sign in">
            <div>
                <ul>
                    <c:forEach items="${requestScope.messages}" var="msg">
                        <c:choose>
                            <c:when test="${msg.type == Message.WARN}">
                                <li style="color: orange">${msg.text}</li>
                            </c:when>
                            <c:when test="${msg.type == Message.INFO}">
                                <li style="color: green">${msg.text}</li>
                            </c:when>
                            <c:when test="${msg.type == Message.ERROR}">
                                <li style="color: red">${msg.text}</li>
                            </c:when>
                            <c:otherwise>
                                <li>${msg.text}</li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>Please login</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello</h1>
                <p>We hope you enjoy our game!</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>


    </body>
</html>

