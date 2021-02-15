package edu.asoldatov.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestBookService {
    /*
    @MockBean
    private UserDao userDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private RoleDao roleDao;

    @Test
    public void testCreateBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Genre genre = new Genre(1L, "genre");

        when(genreDao.getById(1L)).thenReturn(Optional.of(genre));

        CreateBookDtoRequest request = new CreateBookDtoRequest("name", 2000, 1L);

        BookDtoResponse response = bookService.createBook(request);

        Assertions.assertAll(
                () -> verify(bookDao).insert(any()),
                () -> Assertions.assertEquals("name", response.getName()),
                () -> Assertions.assertNull(response.getUser())
        );
    }

    @Test
    public void testUpdateBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Genre genre = new Genre(1L, "genre");

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        when(genreDao.getById(1L)).thenReturn(Optional.of(genre));

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        UpdateBookDtoRequest request = new UpdateBookDtoRequest("new name", 1999, 1L);

        BookDtoResponse response = bookService.updateBook(request, 10L);

        Assertions.assertAll(
                () -> verify(bookDao).update(any()),
                () -> Assertions.assertEquals("new name", response.getName()),
                () -> Assertions.assertNull(response.getUser()),
                () -> Assertions.assertEquals(1999, response.getYearOfPublishing()),
                () -> Assertions.assertEquals(1, response.getGenres().size())
        );
    }

    @Test
    public void testGetAllBooks() {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        List<Book> books = new ArrayList<>();

        books.add(new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>()));

        books.add(new Book(11L, "name2", 2000, null, new HashSet<>(), new HashSet<>()));

        when(bookDao.getAllBook()).thenReturn(books);

        List<BookDtoResponse> responses = bookService.getAllBooks();

        Assertions.assertAll(
                () -> verify(bookDao).getAllBook(),
                () -> Assertions.assertEquals(2, responses.size())
        );
    }

    @Test
    public void testGetBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        BookDtoResponse expectedResponse = new BookDtoResponse(10L, "name", 2000, new ArrayList<>(), new ArrayList<>(), null);

        BookDtoResponse response = bookService.getBook(10L);

        Assertions.assertAll(
                () -> verify(bookDao).getById(10L),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testTakeBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        user.setId(1L);

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        when(userDao.getById(1L)).thenReturn(Optional.of(user));

        BookDtoResponse bookDtoResponse = bookService.takeBook(10L, user);

        Assertions.assertAll(
                () -> verify(bookDao).update(any()),
                () -> Assertions.assertNotNull(bookDtoResponse.getUser())
        );
    }

    @Test
    public void testTakeBookFail() {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        Book book = new Book(10L, "name", 2000, user, new HashSet<>(), new HashSet<>());

        user.setId(1L);

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        when(userDao.getById(1L)).thenReturn(Optional.of(user));

        Assertions.assertThrows(
                ServerException.class, () -> bookService.takeBook(10L, user)
        );
    }

    @Test
    public void testReturnBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        Book book = new Book(10L, "name", 2000, user, new HashSet<>(), new HashSet<>());

        user.setId(1L);

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        when(userDao.getById(1L)).thenReturn(Optional.of(user));

        BookDtoResponse bookDtoResponse = bookService.returnBook(10L, user);

        Assertions.assertAll(
                () -> verify(bookDao).update(any()),
                () -> Assertions.assertNull(bookDtoResponse.getUser())
        );
    }

    @Test
    public void testReturnBookFail() {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        User owner = new User("username", "firstname", "lastname", "patronymic", 2000);

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        Book book = new Book(10L, "name", 2000, owner, new HashSet<>(), new HashSet<>());

        user.setId(1L);

        owner.setId(2L);

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        when(userDao.getById(1L)).thenReturn(Optional.of(user));

        Assertions.assertThrows(
                ServerException.class, () -> bookService.returnBook(10L, user)
        );
    }

    @Test
    public void testAddAuthor() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        when(authorDao.getById(1L)).thenReturn(Optional.of(author));

        AddAuthorToBookDtoRequest request = new AddAuthorToBookDtoRequest(1L);

        BookDtoResponse response = bookService.addAuthor(request, 10L);

        Assertions.assertAll(
                () -> verify(bookDao).update(any()),
                () -> Assertions.assertEquals(1, response.getAuthors().size())
        );
    }

    @Test
    public void testDeleteAuthor() throws ServerException {
        BookService bookService = new BookServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        book.getAuthors().add(author);

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        when(authorDao.getById(1L)).thenReturn(Optional.of(author));

        DeleteAuthorFromBookDtoRequest request= new DeleteAuthorFromBookDtoRequest(1L);

        BookDtoResponse response = bookService.deleteAuthor(request, 10L);

        Assertions.assertAll(
                () -> verify(bookDao).update(any()),
                () -> Assertions.assertEquals(0, response.getAuthors().size())
        );
    }

     */
}
