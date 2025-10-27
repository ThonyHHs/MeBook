package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.UserModel;

public class UserRepository {
    private List<UserModel> userList;

    public UserRepository() {
        userList = new ArrayList<>();
    }

    public List<UserModel> getAll() {
        return userList;
    }

    public UserModel getById(UUID id) {
        for (UserModel user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public UserModel save(UserModel user) {
        userList.add(user);
        return user;
    }

    public UserModel update(UUID id, UserModel updatedUser) {
        for (UserModel user : userList) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setBirthDate(updatedUser.getBirthDate());
            }
        }
        return updatedUser;
    }
    
    public void deleteById(UUID id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(id)) {
                userList.remove(i);
                System.out.println("Usuário excluído com sucesso");
            }
        }
    }
}
