<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${book.name}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<h4><a href="/books">Назад</a></h4>


<div class="jumbotron text-center">
    <h1>
        Название книги: ${book.name}
    </h1>
    <p>Год издания: ${book.yearOfPublishing}</p>
</div>


<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3>
                Жанры:
            </h3>
            <c:forEach items="${book.genres}" var="genres">
                <p>
                    <a href="http://localhost:8080/genres/${genres.id}"> ${genres.name}</a>
                </p>
            </c:forEach>
        </div>

        <div class="col-sm-4">
            <h3>
                Авторы:
            </h3>
            <c:forEach items="${book.authors}" var="authors">
                <a href="http://localhost:8080/authors/${authors.id}"> ${authors.lastName} ${authors.firstName} ${authors.patronymic}</a>
                </p>
            </c:forEach>
        </div>

        <sec:authorize access="hasRole('ADMIN')">
            <div class="col-sm-4">
                <h4>
                    <form:form method="POST" modelAttribute="updateBook">
                        <div>
                            <form:input type="text" path="name" placeholder="Book name" autofocus="true"
                                        value="${book.name}"/>
                            <form:errors path="name"/>
                        </div>

                        <div>
                            <form:input type="number" path="yearOfPublishing" placeholder="Year of Publishing"
                                        value="${book.yearOfPublishing}"/>
                            <form:errors path="yearOfPublishing"/>
                        </div>

                        <div>
                            <form:input type="number" path="genreId" placeholder="Добавить жанр" autofocus="true"/>
                            <form:errors path="genreId"/>
                        </div>


                        <button type="submit">Добавить</button>

                    </form:form>
                </h4>

                <h4>
                    <form:form method="POST" modelAttribute="addAuthor"
                               action="/books/${book.id}/authors">
                        <div>
                            <form:input type="number" path="authorId" placeholder="id автора" autofocus="true"/>
                            <form:errors path="authorId"/>
                        </div>

                        <button type="submit">Добавить</button>
                    </form:form>

                </h4>

                <h4>
                    <form:form method="DELETE" modelAttribute="deleteAuthor"
                               action="http://localhost:8080/books/${book.id}/authors/delete">
                        <div>
                            <form:input type="number" path="deleteAuthorId" placeholder="id автора" autofocus="true"/>
                            <form:errors path="deleteAuthorId"/>
                        </div>

                        <button type="submit">Удалить</button>
                    </form:form>

                </h4>
            </div>
        </sec:authorize>
        <div class="col-sm-4">
            <c:choose>
                <c:when test="${book.user==null}">
                    <h3> Книга свободна!</h3>
                    <br/>
                    <form:form method="POST" action="http://localhost:8080/books/${book.id}/take">
                        <button type="submit">Забронировать книгу!</button>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <h3> Книга занята пользователем ${book.user.username}</h3>
                    <br/>
                    <sec:authentication var="principal" property="principal.username"/>
                    <c:if test="${principal==book.user.username}">
                        <form:form method="POST" action="http://localhost:8080/books/${book.id}/return">
                            <button type="submit">Вернуть книгу!</button>
                        </form:form>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
