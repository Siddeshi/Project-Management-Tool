package org.sid.tool.repos;

import org.sid.tool.models.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends MongoRepository<Like, String> {

    List<Like> findByProjectId(String projectId);

    List<Like> findByFeatureId(String featureId);

    Like findByProjectIdAndUserId(String projectId, String userId);

    Like findByFeatureIdAndUserId(String featureId, String userId);
}
