DROP DATABASE library;
CREATE DATABASE library;

INSERT INTO role
VALUES (1, 'ROLE_USER');
INSERT INTO role
VALUES (2, 'ROLE_ADMIN');
INSERT INTO users_roles
VALUES (1, 2);
INSERT INTO genre VALUES (1, 'фантастика');
INSERT INTO genre VALUES (2, 'любовный роман');

SELECT * FROM author_books;
SELECT * FROM book_authors ;
