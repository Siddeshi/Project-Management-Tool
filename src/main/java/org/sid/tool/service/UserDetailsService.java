package org.sid.tool.service;

import org.sid.tool.models.UserDetails;

public interface UserDetailsService {

    UserDetails findUserDetailsById(String id);

    void createNewUser(UserDetails userDetails);
}
