package org.sid.tool.likes.services;

import org.sid.tool.models.Like;

import java.util.List;

/**
 * Dao interface for project and backlog likes management
 */
public interface LikesServices {

    List<Like> getProjectLikesList(String projectId);

    List<Like> getFeatureLikesList(String featureId);

    String likeProject(String projectId, String userId);

    String likeFeature(String featureId, String userId);

    boolean checkProjectLiked(String projectId, String userId);

    boolean checkFeatureLiked(String featureid, String userId);

    String unlikeProject(String projectId, String userId);

    String unlikeFeature(String featureId, String userid);

    Like getLikedProject(String projectId, String userId);

    Like getLikedFeature(String featureId, String userId);

    void deleteLike(String id);

    String deleteAllProjectLikes(String projectId);

    String deleteAllBacklogLikes(String backlogId);
}
