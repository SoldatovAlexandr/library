<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Genres</title>
</head>
<body>

<h4><a href="/">Назад</a></h4>

<sec:authorize access="hasRole('ADMIN')">
    <h4>
        <form:form method="POST" modelAttribute="genre">
            <div>
                <form:input type="text" path="name" placeholder="name" autofocus="true"/>
                <form:errors path="name"/>
            </div>

            <button type="submit">Добавить</button>

        </form:form>
    </h4>
</sec:authorize>


<table border="1" cellpadding="2">
    <tr>
        <th>Actions</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${genres}" var="genre">
        <tr>
            <td onclick="window.location='http://localhost:8080/genres/${genre.id}'">${genre.id}</td>
            <td>
                    ${genre.name}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
