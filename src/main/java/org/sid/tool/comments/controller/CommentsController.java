package org.sid.tool.comments.controller;

import org.sid.tool.comments.services.CommentsServices;
import org.sid.tool.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class CommentsController {

    private final CommentsServices commentsServices;

    @Autowired
    public CommentsController(CommentsServices commentsServices) {
        this.commentsServices = commentsServices;
    }

    @GetMapping(value = "/comments/projects/{projectId}")
    public ResponseEntity<List<Comment>> getProjectComments(@PathVariable String projectId) {
        return new ResponseEntity<>(commentsServices.getProjectCommentsList(projectId), HttpStatus.OK);
    }

    @GetMapping(value = "/comments/backlogs/{featureId}")
    public ResponseEntity<List<Comment>> getFeatureComments(@PathVariable String featureId) {
        return new ResponseEntity<>(commentsServices.getFeatureCommentsList(featureId), HttpStatus.OK);
    }

    @PostMapping(value = "/comments/projects/{projectId}/new")
    public ResponseEntity<String> commentOnProject(@PathVariable String projectId, @RequestParam String userId,
                                                   @RequestBody String comment) {
        return new ResponseEntity<>(commentsServices.commentOnProject(comment, projectId, userId), HttpStatus.CREATED);
    }

    @PostMapping(value = "/comments/backlogs/{featureId}/new")
    public ResponseEntity<String> commentOnFeature(@PathVariable String featureId, @RequestParam String userId,
                                                   @RequestBody String comment) {
        return new ResponseEntity<>(commentsServices.commentOnFeature(comment, featureId, userId), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/comments/projects/{projectId}/delete")
    public ResponseEntity<String> deleteProjectComment(@PathVariable String projectId, @RequestParam String commentId) {
        return new ResponseEntity<>(commentsServices.deleteProjectComment(projectId, commentId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/comments/features/{featureId}/delete")
    public ResponseEntity<String> deleteFeatureComment(@PathVariable String featureId, @RequestParam String commentId) {
        return new ResponseEntity<>(commentsServices.deleteProjectComment(featureId, commentId), HttpStatus.OK);
    }
}
