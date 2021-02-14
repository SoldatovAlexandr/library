<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>

<h4><a href="/">Назад</a></h4>

<sec:authorize access="hasRole('ADMIN')">
    <h4>
        <form:form method="POST" modelAttribute="book">
            <div>
                <form:input type="text" path="name" placeholder="Book name" autofocus="true"/>
                <form:errors path="name"/>
            </div>

            <div>
                <form:input type="number" path="yearOfPublishing" placeholder="Year of Publishing" autofocus="true"/>
                <form:errors path="yearOfPublishing"/>
            </div>

            <div>
                <form:input type="number" path="genreId" placeholder="Genre id" autofocus="true"/>
                <form:errors path="genreId"/>
            </div>

            <button type="submit">Добавить</button>

        </form:form>
    </h4>
</sec:authorize>

<table border="1" cellpadding="5" class="table">
    <tr>
        <th>Actions</th>
        <th>BookName</th>
        <th>Genres</th>
        <th>Authors</th>
        <th>PublishingYear</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td onclick="window.location='http://localhost:8080/books/${book.id}'">${book.name}</td>
            <td>
                    ${book.name}
            </td>
            <td>
                <c:forEach items="${book.genres}" var="genres">
                    <p onclick="window.location='http://localhost:8080/genres/${genres.id}/'">
                            ${genres.name}
                    </p>
                </c:forEach>
            </td>

            <td>
                <c:forEach items="${book.authors}" var="authors">
                    <p onclick="window.location='http://localhost:8080/authors/${authors.id}/'">
                            ${authors.lastName} ${authors.firstName} ${authors.patronymic}
                    </p>
                </c:forEach>
            </td>

            <td>
                    ${book.yearOfPublishing}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
