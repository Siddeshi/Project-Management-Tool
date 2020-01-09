package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Task implements Serializable {

    @Id
    private ObjectId _id;
    private String taskName;
    private String taskDesc;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(unique = true)
    private Timeline timeline;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private UserDetail userDetail;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private ProductBacklog productBacklog;

    public Task() {
    }

    public Task(ObjectId _id, String taskName, String taskDesc, Timeline timeline, UserDetail userDetail,
                ProductBacklog productBacklog) {
        this._id = _id;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.timeline = timeline;
        this.userDetail = userDetail;
        this.productBacklog = productBacklog;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }
}
