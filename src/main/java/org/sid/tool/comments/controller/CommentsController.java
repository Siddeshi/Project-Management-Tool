package org.sid.tool.comments.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.comments.services.CommentsServices;
import org.sid.tool.customexception.CommentNotFoundException;
import org.sid.tool.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Api(value = "tool", description = "Operations pertaining to comments")
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
    @ApiOperation(value = "Get all the comments on project", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all the comments on the project", response = Comment.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<Comment>> getProjectComments(@PathVariable String projectId) {
        List<Comment> comments = commentsServices.getProjectCommentsList(projectId);
        if (comments.size() == 0) {
            throw new CommentNotFoundException("No comments found for this project");
        } else
            return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Get list of all comments on the product backlog
     *
     * @param featureId id of the product backlog
     * @return List<Comment> list of comments
     */
    @GetMapping(value = "/comments/backlogs/{featureId}")
    @ApiOperation(value = "Get all the comments on backlog", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all the comments on the backlog", response = Comment.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<Comment>> getFeatureComments(@PathVariable String featureId) {
        List<Comment> comments = commentsServices.getFeatureCommentsList(featureId);
        if (comments.size() == 0) {
            throw new CommentNotFoundException("No comments for this backlog");
        } else
            return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Add a new comment on the project
     * @param projectId id of the project
     * @param userId id of the user who commented
     * @param comment actual comment in string
     * @return String status
     */
    @PostMapping(value = "/comments/projects/new")
    @ApiOperation(value = "Add a new comment on project", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new comment added", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> commentOnProject(@RequestParam String projectId, @RequestParam String userId,
                                                   @Valid @RequestBody String comment) {
        return new ResponseEntity<>(commentsServices.commentOnProject(comment, projectId, userId), HttpStatus.CREATED);
    }

    /**
     * Add a new comment on the product backlog
     * @param featureId id of the product backlog
     * @param userId id of the user who commented
     * @param comment actual comment in string
     * @return String status
     */
    @PostMapping(value = "/comments/backlogs/new")
    @ApiOperation(value = "Add a new comment on backlog", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new comment added", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> commentOnFeature(@RequestParam String featureId, @RequestParam String userId,
                                                   @RequestBody String comment) {
        return new ResponseEntity<>(commentsServices.commentOnFeature(comment, featureId, userId), HttpStatus.CREATED);
    }

    /**
     * Delete a comment on the project
     * @param projectId id of the project
     * @param commentId id of the comment
     * @return String status
     */
    @DeleteMapping(value = "/comments/projects/delete")
    @ApiOperation(value = "Delete comment from project", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "comment deleted", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> deleteProjectComment(@RequestParam String projectId, @RequestParam String commentId) {
        return new ResponseEntity<>(commentsServices.deleteProjectComment(projectId, commentId), HttpStatus.OK);
    }

    /**
     * Delete a comment on the product backlog
     * @param featureId id of the product backlog
     * @param commentId id the of the comment
     * @return String status
     */
    @DeleteMapping(value = "/comments/backlogs/delete")
    @ApiOperation(value = "Delete comment from project", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "comment deleted", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> deleteFeatureComment(@RequestParam String featureId, @RequestParam String commentId) {
        return new ResponseEntity<>(commentsServices.deleteProjectComment(featureId, commentId), HttpStatus.OK);
    }

    /**
     * Api is to delete all the comments on the project
     *
     * @param id id of the project
     * @return String status
     */
    @DeleteMapping(value = "/comments/projects/delete/all")
    public ResponseEntity<String> deleteAllProjectComments(@RequestParam String id) {
        return new ResponseEntity<>(commentsServices.deleteAllProjectComments(id), HttpStatus.OK);
    }

    /**
     * Api is to delete all the comments on the backlog
     *
     * @param id id of the backlog
     * @return String status
     */
    @DeleteMapping(value = "/comments/backlogs/delete/all")
    public ResponseEntity<String> deleteAllbacklogComments(@RequestParam String id) {
        return new ResponseEntity<>(commentsServices.deleteAllBacklogComments(id), HttpStatus.OK);
    }
}
