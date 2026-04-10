package edu.eci.dosw.DOSW_Library.controller.mapper;

import edu.eci.dosw.DOSW_Library.controller.dto.UserDTO;
import edu.eci.dosw.DOSW_Library.core.model.Role;
import edu.eci.dosw.DOSW_Library.core.model.User;

public class UserMapper {

    public User toModel(UserDTO dto) {
        Role role = null;
        if (dto.getRole() != null && !dto.getRole().isBlank()) {
            role = Role.valueOf(dto.getRole().toUpperCase());
        }
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getUserName(),
                dto.getPassword(),
                role
        );
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUserName(),
                null,   // nunca exponer el password
                user.getRole() != null ? user.getRole().name() : null
        );
    }

}
