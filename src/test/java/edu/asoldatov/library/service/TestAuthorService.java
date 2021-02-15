package edu.asoldatov.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestAuthorService {
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
    public void testCreateAuthor() {
        AuthorService authorService = new AuthorServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        CreateAuthorDtoRequest request = new CreateAuthorDtoRequest("firstname", "lastname", "patronymic", 2000, "biography");

        AuthorDtoResponse expectedResponse = new AuthorDtoResponse(0L, "firstname", "lastname", "patronymic", 2000, "biography", new HashSet<>());

        AuthorDtoResponse response = authorService.createAuthor(request);

        Assertions.assertAll(
                () -> verify(authorDao).insert(any()),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testUpdateAuthor() throws ServerException {
        AuthorService authorService = new AuthorServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        UpdateAuthorDtoRequest request = new UpdateAuthorDtoRequest("firstname", "lastname", "patronymic", 2000, "biography");

        AuthorDtoResponse expectedResponse = new AuthorDtoResponse(1L, "firstname", "lastname", "patronymic", 2000, "biography", new HashSet<>());

        when(authorDao.getById(1L)).thenReturn(Optional.of(author));

        AuthorDtoResponse response = authorService.updateAuthor(request, 1L);

        Assertions.assertAll(
                () -> verify(authorDao).update(any()),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testGetAuthor() throws ServerException {
        AuthorService authorService = new AuthorServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        AuthorDtoResponse expectedResponse = new AuthorDtoResponse(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new HashSet<>());

        when(authorDao.getById(1L)).thenReturn(Optional.of(author));

        AuthorDtoResponse response = authorService.getAuthor(1L);

        Assertions.assertAll(
                () -> verify(authorDao).getById(1L),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testGetAllAuthors() {
        AuthorService authorService = new AuthorServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        List<Author> authors = new ArrayList<>();

        authors.add(new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>()));

        authors.add(new Author(2L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>()));

        when(authorDao.getAllAuthors()).thenReturn(authors);

        List<AuthorDtoResponse> responses = authorService.getAllAuthors();

        Assertions.assertAll(
                () -> verify(authorDao).getAllAuthors(),
                () -> Assertions.assertEquals(2, responses.size())
        );
    }

    @Test
    public void testAddBookToAuthor() throws ServerException {
        AuthorService authorService = new AuthorServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        AddBookToAuthorDtoRequest request = new AddBookToAuthorDtoRequest(10L);

        when(authorDao.getById(1L)).thenReturn(Optional.of(author));

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        authorService.addBookToAuthor(1L, request);

        Assertions.assertAll(
                () -> verify(authorDao).getById(1L),
                () -> verify(authorDao).update(any())
        );
    }

    @Test
    public void testDeleteBookFromAuthor() throws ServerException {
        AuthorService authorService = new AuthorServiceImpl(bookDao, genreDao, userDao, authorDao, roleDao);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        book.getAuthors().add(author);

        DeleteBookFromAuthorDtoRequest request = new DeleteBookFromAuthorDtoRequest(10L);

        when(authorDao.getById(1L)).thenReturn(Optional.of(author));

        when(bookDao.getById(10L)).thenReturn(Optional.of(book));

        authorService.deleteBookFromAuthor(1L, request);

        Assertions.assertAll(
                () -> verify(authorDao).getById(1L),
                () -> verify(authorDao).update(any())
        );
    }

 */
}
