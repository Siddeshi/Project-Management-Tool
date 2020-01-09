package org.sid.tool.user.services;

import org.sid.tool.models.UserDetail;

import java.util.List;

public interface UserDetailsService {

    UserDetail findUserDetailsByName(String userName);

    UserDetail findUserById(String id);

    List<UserDetail> fetchAllUserDetails();

    UserDetail createNewUser(UserDetail userDetails);

    void deleteUser(String id);

}
