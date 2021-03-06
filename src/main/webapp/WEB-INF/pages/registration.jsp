<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>

    <form:form method="POST" modelAttribute="user">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="username" placeholder="Username" autofocus="true"/>
            <form:errors path="username"/>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"/>
            <form:errors path="password"/>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm" placeholder="Confirm your password"/>
            <form:errors path="passwordConfirm"/>
        </div>

        <div>
            <form:input type="text" path="firstName" placeholder="FirsName" autofocus="true"/>
            <form:errors path="firstName"/>
        </div>
        <div>
            <form:input type="text" path="lastName" placeholder="LastName" autofocus="true"/>
            <form:errors path="lastName"/>
        </div>

        <div>
            <form:input type="text" path="patronymic" placeholder="patronymic" autofocus="true"/>
            <form:errors path="patronymic"/>
        </div>

        <div>
            <form:input type="number" path="yearOfBirth" placeholder="yearOfBirth" autofocus="true"/>
            <form:errors path="yearOfBirth"/>
        </div>

        <button type="submit">Зарегистрироваться</button>
    </form:form>

    <a href="/">Главная</a>
</div>
</body>
</html>