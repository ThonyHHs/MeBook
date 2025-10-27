import java.time.LocalDate;
import java.util.UUID;

import controllers.UserController;
import models.UserModel;
import repositories.UserRepository;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController(new UserService(new UserRepository()));

        userController.save(new UserModel("Anthony", "teste@teste.com", LocalDate.of(2005, 04, 28)));
        userController.save(new UserModel("João", "teste@teste.com", LocalDate.of(2005, 04, 28)));

        UUID toDelete = null;
        for (UserModel user : userController.getAll()) {
            System.out.println(user.toString());
            if (user.getName().equals("João")) {
                toDelete = user.getId();
            }
        }

        if (toDelete != null) {
            userController.delete(toDelete);
        }

        for (UserModel user : userController.getAll()) {
            System.out.println("1. " + user.toString());
        }
    }
}
