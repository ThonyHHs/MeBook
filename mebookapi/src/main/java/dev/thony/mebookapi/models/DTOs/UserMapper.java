package dev.thony.mebookapi.models.DTOs;

import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.models.UserModel;

public class UserMapper {
    public static UserDTO toDto(UserModel user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());
        BookshelfModel bookshelf = user.getBookshelf();
        if (bookshelf != null) {
            userDTO.setBookshelf(BookshelfMapper.toDto(bookshelf));
        }
        return userDTO;
    }
}
