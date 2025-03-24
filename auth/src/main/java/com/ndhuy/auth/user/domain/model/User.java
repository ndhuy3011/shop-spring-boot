package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.domain.model.key.UserKey;
import com.ndhuy.auth.user.domain.valueobject.Address;
import com.ndhuy.auth.user.domain.valueobject.Email;
import com.ndhuy.auth.user.domain.valueobject.Fullname;
import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Phone;
import com.ndhuy.auth.user.domain.valueobject.Username;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "a_user", indexes = {
        @jakarta.persistence.Index(name = "idx_username", columnList = "username")
})
@Entity
@RequiredArgsConstructor
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

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "phone", length = Phone.MAX_LENGTH))
    private Phone phone;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "address", length = Address.MAX_LENGTH))
    private Address address;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "fullName", length = Fullname.MAX_LENGTH))
    private Fullname fullName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", length = Email.MAX_LENGTH, unique = true))
    private Email email;

    
    /** 
     * @param username
     * @param password
     * @return User
     */
    public static User of(String username, String password) {
        return new User(new Username(username), new Password(password));
    }

    public static User of(String username, String password, String email, String phone, String address,
            String fullName) {
        return new User(new Username(username), new Password(password), new Email(email), new Phone(phone),
                new Address(address), new Fullname(fullName));
    }

    private User(Username username, Password password, Email email, Phone phone, Address address, Fullname fullName) {
        this.id = new UserKey();
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.fullName = fullName;
        this.email = email;
    }

    private User(Username username, Password password) {
        this.id = new UserKey();
        this.username = username;
        this.password = password;
    }

}
