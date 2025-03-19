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
     * @param user
     * @return User
     */
    @Override
    public User insert(User user) {
        user.setPassword(Password.of(passwordEncoder.encode(user.getPassword().value())));
        return userRepository.save(user);

    }

    /**
     * @param user
     * @return User
     */
    @Override
    public User update(User user) {

        var userOld = findById(user.getId());
        Objects.requireNonNull(userOld, "USER_NOT_FOUND");

        userOld.setAddress(Objects.isNull(user.getAddress()) ? userOld.getAddress() : user.getAddress());
        userOld.setFullName(Objects.isNull(user.getFullName()) ? userOld.getFullName() : user.getFullName());
        userOld.setPhone(Objects.isNull(user.getPhone()) ? userOld.getPhone() : user.getPhone());

        return userRepository.save(user);
    }

    /**
     * @param user
     * @return User
     */
    @Override
    public User delete(User user) {
        userRepository.delete(user);
        return user;
    }

    /**
     * @param id
     * @return User
     */
    @Override
    public User findById(UserKey id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * @param username
     * @return User
     */
    @Override
    public User findByUsername(Username username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * @return Optional<User>
     */
    @Override
    public Optional<User> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * @param id
     * @param password
     * @return User
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
     * @param username
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        return findByUsername(Username.of(username));
    }

    /**
     * @param id
     * @param user
     * @return User
     */
    @Override
    public User update(UserKey id, User user) {

        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
