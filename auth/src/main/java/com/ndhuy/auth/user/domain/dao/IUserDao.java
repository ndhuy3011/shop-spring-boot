package com.ndhuy.auth.user.domain.dao;

import java.util.Optional;

import com.ndhuy.auth.user.domain.model.User;
import com.ndhuy.auth.user.domain.valueobject.UserKey;
import com.ndhuy.auth.user.domain.valueobject.Username;

public interface IUserDao extends IBaseDao<UserKey,User> {
    public User insert(User user);

    public User update(User user);

    public User updatePassword(UserKey id, String password);

    public User delete(User user);

    public User findById(UserKey id);

    public User findByUsername(Username username);

    public User findByUsername(String username);

    public Optional<User> findAll();

}
