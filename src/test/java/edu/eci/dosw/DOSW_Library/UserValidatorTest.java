package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.model.Role;
import edu.eci.dosw.DOSW_Library.core.model.User;
import edu.eci.dosw.DOSW_Library.core.validator.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidatorTest {

    private UserValidator validator = new UserValidator();

    private User validUser() {
        return new User("U1", "Nombre", "user", "pass", Role.REGULAR_USER);
    }

    @Test
    void validate_shouldPass() {
        assertDoesNotThrow(() -> validator.validate(validUser()));
    }

    @Test
    void validate_shouldFail_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(null);
        });
    }

    @Test
    void validate_shouldFail_whenNameBlank() {
        User user = new User("U1", "", "user", "pass", Role.REGULAR_USER);

        assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(user);
        });
    }

    @Test
    void validate_shouldFail_whenRoleNull() {
        User user = new User("U1", "Nombre", "user", "pass", null);

        assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(user);
        });
    }
}
