<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin</title>
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Patronymic</th>
        <th>YearOfBirth</th>
        <th>UserName</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.patronymic}</td>
                <td>${user.yearOfBirth}</td>
                <td>${user.username}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>

</div>

<p>
    Добавить нового редактора:
    <form:form method="POST" modelAttribute="addAdmin">
<div>
    <form:input type="number" path="id" placeholder="User id" autofocus="true"/>
    <form:errors path="id"/>
</div>

<button type="submit">Добавить</button>
</form:form>
</p>
</body>
</html>
