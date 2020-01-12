package org.sid.tool.user.services;

import org.sid.tool.models.UserDetail;

import java.util.List;

/**
 * Dao interface for user detail
 */
public interface UserDetailsService {

    UserDetail findUserById(String id);

    List<UserDetail> fetchAllUserDetails();

    UserDetail createNewUser(UserDetail userDetails);

    void deleteUser(String id);

}
