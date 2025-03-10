package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.domain.model.key.UserKey;
import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Username;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "a_user", indexes = {
        @jakarta.persistence.Index(name = "idx_username", columnList = "username")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "user_no"))
    private UserKey id;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "username", length = Username.MAX_LENGTH, unique = true, nullable = false))
    private Username username;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password", length = Password.MAX_LENGTH, nullable = false))
    private Password password;

    public User(String username, String password) {
        this.username = new Username(username);
        this.password = new Password(password);
    }

    public User(Username username, Password password) {
        this.id = new UserKey();
        this.username = username;
        setPassword(password);
    }

    public void setPassword(Password password) {
        password.hashPassword();
        this.password = password;
    }

}
