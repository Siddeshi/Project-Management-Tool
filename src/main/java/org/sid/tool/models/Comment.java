package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
public class Comment implements Serializable {

    @Id
    private ObjectId _id;

    private String comment;

    private Date dateOfComment;

    private String userId;

    private String projectId;

    private String featureId;

    public Comment() {
        this.featureId = null;
        this.projectId = null;
        this.userId = null;
    }

    public Comment(ObjectId _id, String comment, Date dateOfComment, String userId, String projectId, String featureId) {
        this._id = _id;
        this.comment = comment;
        this.dateOfComment = dateOfComment;
        this.userId = userId;
        this.projectId = projectId;
        this.featureId = featureId;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }
}
