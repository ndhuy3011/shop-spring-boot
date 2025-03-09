package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.application.dto.RegisterUserDto;
import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.UserKey;
import com.ndhuy.auth.user.domain.valueobject.Username;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private UserKey id;
    @Column
    private Username username;
    @Column
    private Password password;

    public User(Username username, Password password) {
        this.id = new UserKey();
        this.username = username;
        setPassword(password);
    }

    public void setPassword(Password password) {
        password.hashPassword();
        this.password = password;
    }

    public static User of(RegisterUserDto registerUserDto) {
        return new User(registerUserDto.getUsername(), registerUserDto.getPassword());
    }

}
