package com.ndhuy.auth.user.domain.dao.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.User;
import com.ndhuy.auth.user.domain.model.key.UserKey;
import com.ndhuy.auth.user.domain.repository.UserRepository;
import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.StatusUser;
import com.ndhuy.auth.user.domain.valueobject.Username;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Transactional
public class UserDao implements IUserDao {

    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    private UserRepository userRepository;

    /**
     * Inserts a new user into the database.
     *
     * @param user The User object to insert.
     * @return The inserted User object.
     */
    @Override
    public User insert(User user) {
        user.setPassword(Password.of(passwordEncoder.encode(user.getPassword().value())));
        return userRepository.save(user);
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user The User object with updated information.
     * @return The updated User object.
     */
    @Override
    public User update(User user) {
        var userOld = findById(user.getId());
        Objects.requireNonNull(userOld, "USER_NOT_FOUND");

        // Use ternary operator for more concise code
        userOld.setAddress(Objects.isNull(user.getAddress()) ? userOld.getAddress() : user.getAddress());
        userOld.setFullName(Objects.isNull(user.getFullName()) ? userOld.getFullName() : user.getFullName());
        userOld.setPhone(Objects.isNull(user.getPhone()) ? userOld.getPhone() : user.getPhone());

        return userRepository.save(user);
    }

    /**
     * Deletes a user from the database.
     *
     * @param user The User object to delete.
     * @return The deleted User object.  Returning the deleted object is not standard practice.  Consider returning void.
     */
    @Override
    public User delete(User user) {
        userRepository.delete(user);
        return user; // Returning the deleted user might not be necessary
    }

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return The User object with the given ID, or null if not found.
     */
    @Override
    public User findById(UserKey id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The User object with the given username, or null if not found.
     */
    @Override
    public User findByUsername(Username username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Finds all users.  This method is not implemented.
     *
     * @return An Optional containing all users.
     * @throws UnsupportedOperationException If the method is not implemented.
     */
    @Override
    public Optional<User> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Updates the password of a user.
     *
     * @param id       The ID of the user to update.
     * @param password The new password.
     * @return The updated User object.
     */
    @Override
    public User updatePassword(UserKey id, String password) {
        var user = findById(id);
        Objects.requireNonNull(user, "USER_NOT_FOUND");
        Objects.requireNonNull(password, "PASSWORD_IS_REQUIRED");

        if (passwordEncoder.matches(user.getPassword().value(), password)) {
            return user;
        }

        var newPassword = Password.of(password);
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    /**
     * Finds a user by their username (String).
     *
     * @param username The username of the user to find.
     * @return The User object with the given username, or null if not found.
     */
    @Override
    public User findByUsername(String username) {
        return findByUsername(Username.of(username));
    }

    /**
     * Updates a user with new information.  This method is not implemented.
     *
     * @param id   The ID of the user to update.
     * @param user The User object with updated information.
     * @return The updated User object.
     * @throws UnsupportedOperationException If the method is not implemented.
     */
    @Override
    public User update(UserKey id, User user) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Updates the status of a user.
     *
     * @param id     The ID of the user to update.
     * @param status The new status of the user.
     * @return The updated User object.
     */
    @Override
    public User updateStatus(UserKey id, StatusUser status) {
        var user = findById(id);
        Objects.requireNonNull(user, "USER_NOT_FOUND");
        Objects.requireNonNull(status, "Status_IS_REQUIRED");

        user.setStatus(status);
        return userRepository.save(user);
    }
}

