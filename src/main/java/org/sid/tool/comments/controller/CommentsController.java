package org.sid.tool.comments.controller;

import org.sid.tool.comments.services.CommentsServices;
import org.sid.tool.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The CommentsController class is to publish the REST APIs
 * for controlling the comments on projects and product backlog
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
@RestController
@RequestMapping(value = "/")
public class CommentsController {

    /**
     * Autowiring the dao interface
     */
    private final CommentsServices commentsServices;

    @Autowired
    public CommentsController(CommentsServices commentsServices) {
        this.commentsServices = commentsServices;
    }

    /**
     * Get list of all comments on the project
     *
     * @param projectId id of the project
     * @return List<Comment> list of comments
     */
    @GetMapping(value = "/comments/projects/{projectId}")
    public ResponseEntity<List<Comment>> getProjectComments(@PathVariable String projectId) {
        return new ResponseEntity<>(commentsServices.getProjectCommentsList(projectId), HttpStatus.OK);
    }

    /**
     * Get list of all comments on the product backlog
     *
     * @param featureId id of the product backlog
     * @return List<Comment> list of comments
     */
    @GetMapping(value = "/comments/backlogs/{featureId}")
    public ResponseEntity<List<Comment>> getFeatureComments(@PathVariable String featureId) {
        return new ResponseEntity<>(commentsServices.getFeatureCommentsList(featureId), HttpStatus.OK);
    }

    /**
     * Add a new comment on the project
     * @param projectId id of the project
     * @param userId id of the user who commented
     * @param comment actual comment in string
     * @return String status
     */
    @PostMapping(value = "/comments/projects/{projectId}/new")
    public ResponseEntity<String> commentOnProject(@PathVariable String projectId, @RequestParam String userId,
                                                   @RequestBody String comment) {
        return new ResponseEntity<>(commentsServices.commentOnProject(comment, projectId, userId), HttpStatus.CREATED);
    }

    /**
     * Add a new comment on the product backlog
     * @param featureId id of the product backlog
     * @param userId id of the user who commented
     * @param comment actual comment in string
     * @return String status
     */
    @PostMapping(value = "/comments/backlogs/{featureId}/new")
    public ResponseEntity<String> commentOnFeature(@PathVariable String featureId, @RequestParam String userId,
                                                   @RequestBody String comment) {
        return new ResponseEntity<>(commentsServices.commentOnFeature(comment, featureId, userId), HttpStatus.CREATED);
    }

    /**
     * Delete a comment on the project
     * @param projectId id of the project
     * @param commentId id of the comment
     * @return String status
     */
    @DeleteMapping(value = "/comments/projects/{projectId}/delete")
    public ResponseEntity<String> deleteProjectComment(@PathVariable String projectId, @RequestParam String commentId) {
        return new ResponseEntity<>(commentsServices.deleteProjectComment(projectId, commentId), HttpStatus.OK);
    }

    /**
     * Delete a comment on the product backlog
     * @param featureId id of the product backlog
     * @param commentId id the of the comment
     * @return String status
     */
    @DeleteMapping(value = "/comments/features/{featureId}/delete")
    public ResponseEntity<String> deleteFeatureComment(@PathVariable String featureId, @RequestParam String commentId) {
        return new ResponseEntity<>(commentsServices.deleteProjectComment(featureId, commentId), HttpStatus.OK);
    }
}
