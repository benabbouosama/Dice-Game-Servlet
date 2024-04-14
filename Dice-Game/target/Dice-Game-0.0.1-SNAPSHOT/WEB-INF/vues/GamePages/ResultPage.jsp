<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game - Game Over!</title>
    <link href="${pageContext.request.contextPath}/style/formRP.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="form-wrapper">
        <div class="form-container">
            <h4>Game Over!</h4>
            <div class="message-container">
                <ul class="message-list">
                    <c:forEach items="${requestScope.messages}" var="msg">
                        <li class="message ${msg.type}">${msg.text}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="dice-container">
                <table class="dice-table">
                    <thead>
                    <tr>
                        <th>Die 1</th>
                        <th>Die 2</th>
                        <th>Die 3</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${requestScope.dice1}</td>
                        <td>${requestScope.dice2}</td>
                        <td>${requestScope.dice3}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="score-info">
                <p>Your Score : <c:out value="${sessionScope.score}" /></p>
                <p>Best Score : <c:out value="${sessionScope.bestScore}" /></p>
            </div>
            <div class="actions">
                <p>View All Player Scores : <a href="AllPlayersResults">Scores</a></p>
                <form action="RollDice" method="post">
                    <input type="submit" value="New Game" />
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
