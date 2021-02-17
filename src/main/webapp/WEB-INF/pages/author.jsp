<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${author.lastName}</title>
</head>
<body>

<h4><a href="/authors">Назад</a></h4>

<sec:authorize access="hasRole('ADMIN')">
    <h4>
        <form:form method="POST" modelAttribute="updateAuthor">
            <div>
                <form:input type="text" path="firstName" placeholder="firstName" autofocus="true"
                            value="${author.firstName}"/>
                <form:errors path="firstName"/>
            </div>

            <div>
                <form:input type="text" path="lastName" placeholder="lastName" autofocus="true"
                            value="${author.lastName}"/>
                <form:errors path="lastName"/>
            </div>

            <div>
                <form:input type="text" path="patronymic" placeholder="patronymic" autofocus="true"
                            value="${author.patronymic}"/>
                <form:errors path="patronymic"/>
            </div>

            <div>
                <form:input type="number" path="yearOfBirth" placeholder="Year of Birthdate" autofocus="true"
                            value="${author.yearOfBirth}"/>
                <form:errors path="yearOfBirth"/>
            </div>

            <div>
                <form:input type="text" path="biography" placeholder="biography" autofocus="true"
                            value="${author.biography}"/>
                <form:errors path="biography"/>
            </div>

            <button type="submit">Обновить</button>

        </form:form>
    </h4>
</sec:authorize>

<p>
    ${author.lastName} ${author.firstName} ${author.patronymic}
</p>
<p>
    Биография:
    ${author.biography}
</p>
<p>
    Год рождения: ${author.yearOfBirth}
</p>

<p>
    Список книг автора:

    <c:forEach items="${author.books}" var="book">
        <a href="/books/${book.id}/"> ${book.name}</a>
    </c:forEach>
</p>
</body>
</html>
