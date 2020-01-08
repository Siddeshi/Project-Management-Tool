package org.sid.tool.repos;

import org.sid.tool.models.Timeline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineRepository extends MongoRepository<Timeline, String> {
}
