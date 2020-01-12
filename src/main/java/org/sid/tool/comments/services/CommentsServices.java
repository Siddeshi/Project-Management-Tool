package org.sid.tool.comments.services;

import org.sid.tool.models.Comment;

import java.util.List;


/**
 * The CommentsServices interface is for transaction management
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
public interface CommentsServices {

    List<Comment> getProjectCommentsList(String projectId);

    List<Comment> getFeatureCommentsList(String featureId);

    String commentOnProject(String comment, String projectId, String userId);

    String commentOnFeature(String comment, String featureId, String userId);

    String deleteProjectComment(String projectId, String commentId);

    String deleteFeatureComment(String featureId, String commentId);
}
