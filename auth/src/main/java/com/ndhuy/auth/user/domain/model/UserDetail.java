package com.ndhuy.auth.user.domain.model;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Username;

public class UserDetail implements UserDetails, CredentialsContainer {

    private transient Password password;
    private transient Username username;

    public UserDetail(Username username, Password password) {
        this.username = username;
        this.password = password;
    }


    public static UserDetail of(User user) {
        return new UserDetail(user.getUsername(), user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
 
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        return password.value();
    }

    @Override
    public String getUsername() {
        return username.value();
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

}
