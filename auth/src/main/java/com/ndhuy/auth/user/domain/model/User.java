package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.domain.model.key.UserKey;
import com.ndhuy.auth.user.domain.valueobject.Address;
import com.ndhuy.auth.user.domain.valueobject.Email;
import com.ndhuy.auth.user.domain.valueobject.Fullname;
import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Phone;
import com.ndhuy.auth.user.domain.valueobject.StatusUser;
import com.ndhuy.auth.user.domain.valueobject.Username;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Represents a user in the system. This entity maps to the "a_user" database
 * table.
 */
@Table(name = "a_user", indexes = {
        @jakarta.persistence.Index(name = "idx_username", columnList = "username") // Index for username
})
@Entity
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments

@Getter
@Setter
public class User {

    // EmbeddedId indicates that this is a composite primary key
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "user_no")) // Override the column name for the 'value' field of UserKey
    private UserKey id;

    // Embedded indicates that Username is a value object
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

    // @Enumerated(EnumType.STRING) indicates that the status is stored as a string
    // in the database
    @Enumerated(EnumType.STRING)
    private StatusUser status = StatusUser.CLOSE;

    /**
     * Static factory method to create a User with username and password.
     *
     * @param username The username string.
     * @param password The password string.
     * @return A new User instance.
     */
    public static User of(String username, String password) {
        return new User(new Username(username), new Password(password));
    }

    /**
     * Static factory method to create a User with all details.
     *
     * @param username The username string.
     * @param password The password string.
     * @param email    The email string.
     * @param phone    The phone number string.
     * @param address  The address string.
     * @param fullName The full name string.
     * @return A new User instance.
     */
    public static User of(String username, String password, String email, String phone, String address,
            String fullName) {
        return new User(new Username(username), new Password(password), new Email(email), new Phone(phone),
                new Address(address), new Fullname(fullName));
    }

    // Private constructor used by the of methods and JPA. Marked private to enforce
    // use of the static factory methods for object creation.
    private User(Username username, Password password, Email email, Phone phone, Address address, Fullname fullName) {
        this.id = new UserKey();
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.fullName = fullName;
        this.email = email;
    }

    // Private constructor used by the of method and JPA.
    private User(Username username, Password password) {
        this.id = new UserKey();
        this.username = username;
        this.password = password;
    }
}
