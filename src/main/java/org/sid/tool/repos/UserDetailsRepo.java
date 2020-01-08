package org.sid.tool.repos;

import org.sid.tool.models.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepo extends MongoRepository<UserDetails, String> {
}