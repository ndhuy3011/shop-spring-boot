package com.ndhuy.auth.user.domain.dao;

import java.util.Optional;

import com.ndhuy.auth.user.domain.model.User;
import com.ndhuy.auth.user.domain.model.key.UserKey;
import com.ndhuy.auth.user.domain.valueobject.StatusUser;
import com.ndhuy.auth.user.domain.valueobject.Username;

public interface IUserDao extends IBaseDao<UserKey,User> {
    
    /**
     * Inserts a new user into the database.
     *
     * @param user The User object to insert.
     * @return The inserted User object.
     */
    public User insert(User user);
    /**
     * Updates an existing user in the database.
     *
     * @param user The User object with updated information.
     * @return The updated User object.
     */
    public User update(User user);
    /**
     * Updates the password of a user.
     *
     * @param id       The ID of the user to update.
     * @param password The new password.
     * @return The updated User object.
     */
    public User updatePassword(UserKey id, String password);
   /**
     * Deletes a user from the database.
     *
     * @param user The User object to delete.
     * @return The deleted User object.  Returning the deleted object is not standard practice.  Consider returning void.
     */
    public User delete(User user);
   /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return The User object with the given ID, or null if not found.
     */
    public User findById(UserKey id);
   /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The User object with the given username, or null if not found.
     */
    public User findByUsername(Username username);
    /**
     * Finds a user by their username (String).
     *
     * @param username The username of the user to find.
     * @return The User object with the given username, or null if not found.
     */
    public User findByUsername(String username);

    /**
     * Finds all users.  This method is not implemented.
     *
     * @return An Optional containing all users.
     * @throws UnsupportedOperationException If the method is not implemented.
     */
    public Optional<User> findAll();

    /**
     * Updates the status of a user.
     *
     * @param id     The ID of the user to update.
     * @param status The new status of the user.
     * @return The updated User object.
     */
    public User updateStatus(UserKey id, StatusUser status);

}
