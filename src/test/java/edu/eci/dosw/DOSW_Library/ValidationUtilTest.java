package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.util.ValidationUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationUtilTest {
    @Test
    void validateNotNull_shouldThrowException_whenObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.validateNotNull(null, "Object cannot be null");
        });
    }

    @Test
    void validateNotNull_shouldPass_whenObjectIsNotNull() {
        assertDoesNotThrow(() -> {
            ValidationUtil.validateNotNull(new Object(), "Object cannot be null");
        });
    }

    @Test
    void validateNotBlank_shouldThrowException_whenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.validateNotBlank(null, "Value cannot be blank");
        });
    }

    @Test
    void validateNotBlank_shouldThrowException_whenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.validateNotBlank("", "Value cannot be blank");
        });
    }

    @Test
    void validateNotBlank_shouldThrowException_whenValueIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.validateNotBlank("   ", "Value cannot be blank");
        });
    }

    @Test
    void validateNotBlank_shouldPass_whenValueIsValid() {
        assertDoesNotThrow(() -> {
            ValidationUtil.validateNotBlank("B1", "Value cannot be blank");
        });
    }

    
}
