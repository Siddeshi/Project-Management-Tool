package org.sid.tool.comments.services;

import org.sid.tool.customexception.CommentNotFoundException;
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

@Service
@Transactional
public class CommentsServicesImpl implements CommentsServices {

    private final CommentsRepository commentsRepository;

    private final ProjectDetailsService projectDetailsService;

    private final ProductBacklogService backlogService;

    @Autowired
    public CommentsServicesImpl(CommentsRepository commentsRepository, ProjectDetailsService projectDetailsService,
                                ProductBacklogService backlogService) {
        this.commentsRepository = commentsRepository;
        this.projectDetailsService = projectDetailsService;
        this.backlogService = backlogService;
    }

    @Override
    public List<Comment> getProjectCommentsList(String projectId) {
        return commentsRepository.findByProjectId(projectId);
    }

    @Override
    public List<Comment> getFeatureCommentsList(String featureId) {
        return commentsRepository.findByFeatureId(featureId);
    }

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

    @Override
    public String deleteProjectComment(String projectId, String commentId) {
        Comment comment = commentsRepository.findOne(commentId);
        if (comment == null) {
            throw new CommentNotFoundException("Comment not found for the id-" + commentId);
        } else {
            commentsRepository.delete(commentId);
            ProjectDetails projectDetails = projectDetailsService.getProjectById(projectId);
            projectDetails.setCommentsCount(projectDetails.getCommentsCount() - 1);
            projectDetailsService.updateProjectDetails(projectDetails);
        }
        return "comment deleted";
    }

    @Override
    public String deleteFeatureComment(String featureId, String commentId) {
        Comment comment = commentsRepository.findOne(commentId);
        if (comment == null) {
            throw new CommentNotFoundException("Comment not found for the id-" + commentId);
        } else {
            commentsRepository.delete(commentId);
            ProductBacklog productBacklog = backlogService.findProductBacklogById(featureId);
            productBacklog.setCommentsCount(productBacklog.getCommentsCount() - 1);
            backlogService.updateBacklog(productBacklog);
        }
        return "comment deleted";
    }
}
