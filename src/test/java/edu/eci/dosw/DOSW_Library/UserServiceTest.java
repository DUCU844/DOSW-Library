package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.model.Role;
import edu.eci.dosw.DOSW_Library.core.model.User;
import edu.eci.dosw.DOSW_Library.core.service.UserService;
import edu.eci.dosw.DOSW_Library.core.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(new UserValidator());
    }

    private User createUser(String id) {
        return new User(id, "Nombre", "username", "password", Role.REGULAR_USER);
    }

    @Test
    void addUser_shouldAddSuccessfully() {
        userService.addUser(createUser("U1"));
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void getUserById_shouldReturnUser() {
        userService.addUser(createUser("U1"));

        assertEquals("U1", userService.getUserById("U1").getId());
    }

    @Test
    void getUserById_shouldThrowException_whenNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userService.getUserById("NO_EXISTE");
        });
    }

    @Test
    void updateUser_shouldUpdateUserName() {
        userService.addUser(createUser("U1"));

        User updated = new User(
                "U1",
                "Nombre",
                "NuevoUserName",
                "password",
                Role.REGULAR_USER
        );

        userService.updateUser("U1", updated);

        assertEquals("NuevoUserName",
                userService.getUserById("U1").getUserName());
    }

    @Test
    void deleteUser_shouldRemoveUser() {
        userService.addUser(createUser("U1"));

        userService.deleteUser("U1");

        assertTrue(userService.getAllUsers().isEmpty());
    }
}