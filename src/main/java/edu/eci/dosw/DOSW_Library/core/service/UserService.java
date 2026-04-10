package edu.eci.dosw.DOSW_Library.core.service;


import edu.eci.dosw.DOSW_Library.core.exception.UserNotFoundException;
import edu.eci.dosw.DOSW_Library.core.model.User;
import edu.eci.dosw.DOSW_Library.core.util.ValidationUtil;
import edu.eci.dosw.DOSW_Library.core.validator.LoanValidator;
import edu.eci.dosw.DOSW_Library.core.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private final UserValidator userValidator;

    public UserService(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public void addUser(User user) {
        userValidator.validate(user);
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(String id) {
        ValidationUtil.validateNotBlank(id, "El ID no puede ser null");
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new UserNotFoundException("User not found" + id));
    }

    public void updateUser(String id, User updateUser) {
        ValidationUtil.validateNotBlank(id, "El ID no puede ser null");
        userValidator.validate(updateUser);
        User user =  getUserById(id);
        user.setUserName(updateUser.getUserName());
    }

    public void deleteUser(String id) {
        ValidationUtil.validateNotBlank(id, "El ID no puede ser null");
        User user =  getUserById(id);
        users.remove(user);
    }
}
