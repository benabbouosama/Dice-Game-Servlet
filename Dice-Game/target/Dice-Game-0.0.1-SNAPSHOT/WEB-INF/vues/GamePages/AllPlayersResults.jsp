<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game - Best Scores</title>
    <link href="${pageContext.request.contextPath}/style/formR.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="header">
        <h4>Best Scores</h4>
    </div>
    <div class="score-table">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>ID</th>
                <th>Score</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.appPlayers}" var="player">
                <tr>
                    <td>${player.nom} ${player.prenom}</td>
                    <td>${player.login}</td>
                    <td>${player.playerStatus.bestScore}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
