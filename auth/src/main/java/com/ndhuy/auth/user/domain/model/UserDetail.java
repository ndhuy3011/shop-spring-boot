package com.ndhuy.auth.user.domain.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.StatusUser;
import com.ndhuy.auth.user.domain.valueobject.Username;

public class UserDetail implements UserDetails, CredentialsContainer {

    private transient Username username;
    private transient Password password;
    private Set<GrantedAuthority> authorities = new HashSet<>(); // Use HashSet for better performance
    private StatusUser status;

    // Constructor with StatusUser
    public UserDetail(Username username, Password password, Set<Role> roles, StatusUser status) {
        this.username = username;
        this.password = password;
        setAuthorities(roles); // Initialize authorities here
        this.status = status;
    }

    // Constructor without StatusUser.  Added this as the previous code had a constructor with username, password, and roles, but not status.
    public UserDetail(Username username, Password password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        setAuthorities(roles); // Initialize authorities here
        this.status = StatusUser.NORMAL; // Default status.  Consider making this a parameter if the default status can change
    }

    // Constructor with username and password
    public UserDetail(Username username, Password password) {
        this.username = username;
        this.password = password;
        this.status = StatusUser.NORMAL; //Added default status
    }

    /**
     * Sets the authorities for a single role.
     *
     * @param role The role to set the authority for.
     */
    public void setAuthorities(Role role) {
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role.getIdRole().value()));
        }
    }

    /**
     * Sets the authorities for a set of roles.
     *
     * @param roles The set of roles to set the authorities for.
     */
    public void setAuthorities(Set<Role> roles) {
        if (roles != null && !roles.isEmpty()) { // Added null and empty check
            for (Role role : roles) {
                setAuthorities(role); // Reuse the single role method
            }
        }
    }

    /**
     * Creates a UserDetail object from a User object.
     *
     * @param user  The User object.
     * @param roles The set of roles for the user.
     * @return A UserDetail object.
     */
    public static UserDetail of(User user, Set<Role> roles) {
        return new UserDetail(user.getUsername(), user.getPassword(), roles, user.getStatus()); //Assumed user has getStatus()
    }

    /**
     * Creates a UserDetail object from a User object.
     *
     * @param user The User object.
     * @return A UserDetail object.
     */
    public static UserDetail of(User user) {
        return new UserDetail(user.getUsername(), user.getPassword(), new HashSet<>(), StatusUser.NORMAL); // Added empty role set and default status.  Ensure User has getStatus()
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean isEnabled() {
      return status == StatusUser.NORMAL; 
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
