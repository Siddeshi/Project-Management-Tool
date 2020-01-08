package org.sid.tool.repos;

import org.sid.tool.models.Milestone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestonesRepository extends MongoRepository<Milestone, String> {
}
