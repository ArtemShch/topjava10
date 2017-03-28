<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 28.03.17
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
<h2><a href="index.html">Home</a></h2>
<table class="table table-striped">
    <tr>
        <th>Дата и время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <c:if test="${meal.isExceed()}">
            <tr class="danger">
                <td>${meal.getDateTimeWithoutT()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
        </c:if>

        <c:if test="${!meal.isExceed()}">
            <tr>
                <td>${meal.getDateTimeWithoutT()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</div>
</body>
</html>
