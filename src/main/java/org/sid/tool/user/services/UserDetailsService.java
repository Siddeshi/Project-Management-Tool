package org.sid.tool.user.services;

import org.bson.types.ObjectId;
import org.sid.tool.models.UserDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDetailsService {

    ResponseEntity<UserDetail> findUserDetailsByName(String userName);

    ResponseEntity<UserDetail> findUserById(ObjectId id);

    ResponseEntity<List<UserDetail>> fetchAllUserDetails();

    ResponseEntity<Object> createNewUser(UserDetail userDetails);
}
