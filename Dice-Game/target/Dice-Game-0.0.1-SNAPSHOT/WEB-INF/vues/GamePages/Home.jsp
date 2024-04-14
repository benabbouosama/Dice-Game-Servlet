<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <link href="${pageContext.request.contextPath}/style/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/formH.css" rel="stylesheet">
</head>
<body>
<div class="form-wrapper">
    <div class="form-container">
        <h4>Logged in user: <c:out value="${sessionScope.onlinePlayer.login}" /></h4>
        <form action="RollDice" method="post">
            <p>Die number:
                <select id="diceNumber" name="diceNumber" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </p>
            <p><input type="submit" value="Roll the die"> </p>
        </form>
        <p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">Die 1</th>
                <th scope="col">Die 2</th>
                <th scope="col">Die 3</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${onlinePlayer.playerStatus.attempts[0]}</td>
                <td>${onlinePlayer.playerStatus.attempts[1]}</td>
                <td>${onlinePlayer.playerStatus.attempts[2]}</td>
            </tr>
        </table>
        </p>
        <p>View scores of all players: <a href="AllPlayersResults">Scores</a></p>
        <form action="DecoServlet" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</div>
</body>
</html>
