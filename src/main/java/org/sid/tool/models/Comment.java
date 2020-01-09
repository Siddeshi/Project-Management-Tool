package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;

@Document
public class Comment implements Serializable {

    @Id
    private ObjectId _id;

    private String comment;

    private Date dateOfComment;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private ProjectDetails projectDetails;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private UserDetail userDetail;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private ProductBacklog productBacklog;

    public Comment() {
    }

    public Comment(ObjectId _id, String comment, Date dateOfComment) {
        this._id = _id;
        this.comment = comment;
        this.dateOfComment = dateOfComment;
    }

    public ObjectId get_id() {
        return _id;
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
}
