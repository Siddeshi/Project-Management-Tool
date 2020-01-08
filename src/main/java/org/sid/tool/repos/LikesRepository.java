package org.sid.tool.repos;

import org.sid.tool.models.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository<Like, String> {
}
