package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;

@Document
public class Comment implements Serializable {

    @Id
    private String commentId;

    private String projectId;

    private String userId;

    private String comment;

    private Date dateOfComment;

    public Comment(String commentId, String projectId, String userId, String comment, Date dateOfComment) {
        this.commentId = commentId;
        this.projectId = projectId;
        this.userId = userId;
        this.comment = comment;
        this.dateOfComment = dateOfComment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(Date dateOfComment) {
        this.dateOfComment = dateOfComment;
    }
}
