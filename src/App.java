import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import controllers.BookController;
import controllers.UserController;
import models.BookModel;
import models.UserModel;
import models.enums.GenreEnum;
import repositories.BookRepository;
import repositories.UserRepository;
import services.BookService;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController(new UserService(new UserRepository()));
        BookController bookController = new BookController(new BookService(new BookRepository()));

        userController.save(new UserModel(
            "Alice Smith", 
            "alice.smith@example.com", 
            LocalDate.parse("1990-05-15")
        ));
        userController.save(new UserModel(
            "Bob Johnson", 
            "b.johnson@example.com", 
            LocalDate.parse("1985-11-22")
        ));
        userController.save(new UserModel(
            "Charlie Lee", 
            "charlie.lee@example.com", 
            LocalDate.parse("2001-01-30")
        ));
        
        bookController.save(new BookModel(
            "Dune",
            "A mythic and emotionally charged hero's journey.",
            "Frank Herbert",
            LocalDate.parse("1965-08-01"),
            new ArrayList<>(Arrays.asList(GenreEnum.ScienceFiction))
        ));
        bookController.save(new BookModel(
            "The Pragmatic Programmer",
            "From journeyer to master.",
            "Andrew Hunt, David Thomas",
            LocalDate.parse("1999-10-20"),
            new ArrayList<>(Arrays.asList(GenreEnum.Literary))
        ));
        bookController.save(new BookModel(
            "The Name of the Wind",
            "A hero's journey in a world of magic and mystery.",
            "Patrick Rothfuss",
            LocalDate.parse("2007-03-27"),
            new ArrayList<>(Arrays.asList(GenreEnum.Fantasy, GenreEnum.Adventure))
        ));


        System.out.println(userController.getAll().toString());
        System.out.println(bookController.getAll().toString());
        
        UserModel userTest = userController.getAll().get(0);
        BookModel bookTest = bookController.getAll().get(0);
        userController.update(userTest.getId(), new UserModel("It changed", "noahss@example.com", userTest.getBirthDate()));
        bookController.update(bookTest.getId(), new BookModel("It changed", "yay", bookTest.getAuthor(), bookTest.getReleaseDate(), bookTest.getGenres()));
        
        System.out.println(userController.getAll().toString());
        System.out.println(bookController.getAll().toString());
        
        userController.delete(userTest.getId());
        bookController.delete(bookTest.getId());
        
        System.out.println(userController.getAll().toString());
        System.out.println(bookController.getAll().toString());
    }
}
