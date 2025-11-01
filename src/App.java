import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import controllers.BookController;
import controllers.BookshelfController;
import controllers.UserController;
import models.BookModel;
import models.UserModel;
import models.enums.GenreEnum;
import repositories.BookRepository;
import repositories.BookshelfRepository;
import repositories.UserRepository;
import services.BookService;
import services.BookshelfService;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        UserService userService = new UserService(new UserRepository());
        BookService bookService = new BookService(new BookRepository());
        BookshelfService bookshelfService =
                new BookshelfService(new BookshelfRepository(), bookService, userService);

        BookController bookController = new BookController(bookService);
        BookshelfController bookshelfController = new BookshelfController(bookshelfService);
        UserController userController = new UserController(userService, bookshelfService);

        // Create users
        userController.save(new UserModel(
                "Alice Smith",
                "alice.smith@example.com",
                LocalDate.parse("1990-05-15")));
        userController.save(new UserModel(
                "Bob Johnson",
                "b.johnson@example.com",
                LocalDate.parse("1985-11-22")));
        userController.save(new UserModel(
                "Charlie Lee",
                "charlie.lee@example.com",
                LocalDate.parse("2001-01-30")));

        // Create books
        bookController.save(new BookModel(
                "Dune",
                "A mythic and emotionally charged hero's journey.",
                "Frank Herbert",
                LocalDate.parse("1965-08-01"),
                new ArrayList<>(Arrays.asList(GenreEnum.ScienceFiction))));
        bookController.save(new BookModel(
                "The Pragmatic Programmer",
                "From journeyer to master.",
                "Andrew Hunt, David Thomas",
                LocalDate.parse("1999-10-20"),
                new ArrayList<>(Arrays.asList(GenreEnum.Literary))));
        bookController.save(new BookModel(
                "The Name of the Wind",
                "A hero's journey in a world of magic and mystery.",
                "Patrick Rothfuss",
                LocalDate.parse("2007-03-27"),
                new ArrayList<>(Arrays.asList(GenreEnum.Fantasy, GenreEnum.Adventure))));

        // Test data
        UserModel userTest = userController.getAll().get(0);
        BookModel bookTest1 = bookController.getAll().get(0);
        BookModel bookTest2 = bookController.getAll().get(2);

        System.out.println(userController.getAll());
        System.out.println(bookController.getAll());
        System.out.println(bookshelfController.getAll());

        // Create bookshelf and add books
        userController.createBookshelf(userTest.getId(), true);
        bookshelfController.addBook(userTest.getBookshelfId(), bookTest1.getId());
        bookshelfController.addBook(userTest.getBookshelfId(), bookTest2.getId());

        System.out.println(userController.getAll());
        System.out.println(bookController.getAll());
        System.out.println(bookshelfController.getAll());

        // Update data
        userController.update(userTest.getId(),
                new UserModel("It changed", "noahss@example.com", userTest.getBirthDate()));

        bookController.update(bookTest1.getId(),
                new BookModel(
                        "It changed",
                        "yay",
                        bookTest1.getAuthor(),
                        bookTest1.getReleaseDate(),
                        bookTest1.getGenres()));

        System.out.println(userController.getAll());
        System.out.println(bookController.getAll());
        System.out.println(bookshelfController.getAll());

        // Delete data
        userController.delete(userTest.getId());
        bookController.delete(bookTest1.getId());

        System.out.println(userController.getAll());
        System.out.println(bookController.getAll());
        System.out.println(bookshelfController.getAll());
    }
}
