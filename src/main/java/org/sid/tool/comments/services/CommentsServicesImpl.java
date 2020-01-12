package org.sid.tool.comments.services;

import org.sid.tool.customexception.BacklogModificationException;
import org.sid.tool.customexception.CommentNotFoundException;
import org.sid.tool.customexception.ProjectModificationException;
import org.sid.tool.feature.services.ProductBacklogService;
import org.sid.tool.models.Comment;
import org.sid.tool.models.ProductBacklog;
import org.sid.tool.models.ProjectDetails;
import org.sid.tool.projects.services.ProjectDetailsService;
import org.sid.tool.repos.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Dao layer implementation class is for handling comments related transactions
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
@Service
@Transactional
public class CommentsServicesImpl implements CommentsServices {

    /**
     * Autowiring the comments repository
     */
    private final CommentsRepository commentsRepository;

    /**
     * Autowiring the project details dao interface
     */
    private final ProjectDetailsService projectDetailsService;

    /**
     * Autowiring the prodcut backlog dao interface
     */
    private final ProductBacklogService backlogService;

    @Autowired
    public CommentsServicesImpl(CommentsRepository commentsRepository, ProjectDetailsService projectDetailsService,
                                ProductBacklogService backlogService) {
        this.commentsRepository = commentsRepository;
        this.projectDetailsService = projectDetailsService;
        this.backlogService = backlogService;
    }

    /**
     * Get list of all comments on the project
     *
     * @param projectId id of the project
     * @return List<Comment> list of comments
     */
    @Override
    public List<Comment> getProjectCommentsList(String projectId) {
        return commentsRepository.findByProjectId(projectId);
    }

    /**
     * Get list of all comments on the product backlog
     *
     * @param featureId id of the product backlog
     * @return List<Comment> list of comments
     */
    @Override
    public List<Comment> getFeatureCommentsList(String featureId) {
        return commentsRepository.findByFeatureId(featureId);
    }

    /**
     * Add a new comment on the project
     * @param comment actual comment in string
     * @param projectId id of the project
     * @param userId id of the user who commented
     * @return String status
     */
    @Override
    public String commentOnProject(String comment, String projectId, String userId) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setDateOfComment(new Date(System.currentTimeMillis()));
        newComment.setProjectId(projectId);
        newComment.setUserId(userId);
        commentsRepository.save(newComment);
        ProjectDetails projectDetails = projectDetailsService.getProjectById(projectId);
        projectDetails.setCommentsCount(projectDetails.getCommentsCount() + 1);
        projectDetailsService.updateProjectDetails(projectDetails);
        return "new comment added";
    }

    /**
     * Add a new comment on the product backlog
     * @param comment actual comment in string
     * @param featureId id of the product backlog
     * @param userId id of the user who commented
     * @return String status
     */
    @Override
    public String commentOnFeature(String comment, String featureId, String userId) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setDateOfComment(new Date(System.currentTimeMillis()));
        newComment.setFeatureId(featureId);
        newComment.setUserId(userId);
        commentsRepository.save(newComment);
        ProductBacklog backlog = backlogService.findProductBacklogById(featureId);
        backlog.setCommentsCount(backlog.getCommentsCount() + 1);
        backlogService.updateBacklog(backlog);
        return "new comment added";
    }

    /**
     * Delete a comment on the project
     * @param projectId id of the project
     * @param commentId id of the comment
     * @return String status
     * @exception CommentNotFoundException throws this when id of the comment is not found in the repository
     */
    @Override
    public String deleteProjectComment(String projectId, String commentId) {
        Comment comment = commentsRepository.findOne(commentId);
        if (comment == null) {
            throw new CommentNotFoundException("Comment not found for the id-" + commentId);
        } else {
            if (!comment.getProjectId().equals(projectId)) {
                throw new ProjectModificationException("Project id not match with the comment");
            } else {
                commentsRepository.delete(commentId);
                ProjectDetails projectDetails = projectDetailsService.getProjectById(projectId);
                projectDetails.setCommentsCount(projectDetails.getCommentsCount() - 1);
                projectDetailsService.updateProjectDetails(projectDetails);
            }
        }
        return "comment deleted";
    }

    /**
     * Delete a comment on the product backlog
     * @param featureId id of the product backlog
     * @param commentId id the of the comment
     * @return String status
     * @exception CommentNotFoundException throws this when id of the comment is not found in the repository
     */
    @Override
    public String deleteFeatureComment(String featureId, String commentId) {
        Comment comment = commentsRepository.findOne(commentId);
        if (comment == null) {
            throw new CommentNotFoundException("Comment not found for the id-" + commentId);
        } else {
            if (!comment.getFeatureId().equals(featureId)) {
                throw new BacklogModificationException("Backlog id doesn't match with comment");
            } else {
                commentsRepository.delete(commentId);
                ProductBacklog productBacklog = backlogService.findProductBacklogById(featureId);
                productBacklog.setCommentsCount(productBacklog.getCommentsCount() - 1);
                backlogService.updateBacklog(productBacklog);
            }
        }
        return "comment deleted";
    }
}
