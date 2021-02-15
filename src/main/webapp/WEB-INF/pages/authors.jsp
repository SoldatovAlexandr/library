<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>

<h4><a href="/">Назад</a></h4>

<sec:authorize access="hasRole('ADMIN')">
    <h4>
        <form:form method="POST" modelAttribute="author">
            <div>
                <form:input type="text" path="firstName" placeholder="firstName" autofocus="true"/>
                <form:errors path="firstName"/>
            </div>

            <div>
                <form:input type="text" path="lastName" placeholder="lastName" autofocus="true"/>
                <form:errors path="firstName"/>
            </div>

            <div>
                <form:input type="text" path="patronymic" placeholder="patronymic" autofocus="true"/>
                <form:errors path="firstName"/>
            </div>

            <div>
                <form:input type="number" path="yearOfBirth" placeholder="Year of Birthdate" autofocus="true"/>
                <form:errors path="yearOfBirth"/>
            </div>

            <div>
                <form:input type="text" path="biography" placeholder="biography" autofocus="true"/>
                <form:errors path="biography"/>
            </div>

            <button type="submit">Добавить</button>

        </form:form>
    </h4>
</sec:authorize>


<table border="1" cellpadding="6">
    <tr>
        <th>Actions</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Patronymic</th>
        <th>Biography</th>
        <th>YeasOfBirth</th>
    </tr>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td>
                <a href="/authors/${author.id}">
                        ${author.id}
                </a>
            </td>
            <td>
                    ${author.firstName}
            </td>
            <td>
                    ${author.lastName}
            </td>
            <td>
                    ${author.patronymic}
            </td>
            <td>
                    ${author.biography}
            </td>
            <td>
                    ${author.yearOfBirth}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
