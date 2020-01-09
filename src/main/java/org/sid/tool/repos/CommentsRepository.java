package org.sid.tool.repos;

import org.sid.tool.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends MongoRepository<Comment, String> {

    List<Comment> findByProjectIdAndUserId(String projectId, String userId);

    List<Comment> findByFeatureIdAndUserId(String featureId, String userId);

    List<Comment> findByProjectId(String projectId);

    List<Comment> findByFeatureId(String featureId);
}
