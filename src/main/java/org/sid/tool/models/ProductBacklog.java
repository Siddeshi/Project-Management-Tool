package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class ProductBacklog implements Serializable {

    @Id
    private ObjectId _id;

    private String featureName;

    private String featureDesc;

    private long likesCount;

    private long commentsCount;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<ProjectDocument> projectDocuments;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<Task> tasks;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private ProjectDetails projectDetails;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private Team team;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private Milestone milestone;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "productBacklog", cascade = CascadeType.ALL)
    //private List<Comment> comments;

    public ProductBacklog() {
    }

    public ProductBacklog(ObjectId _id, String featureName, String featureDesc, long likesCount, long commentsCount,
                          List<ProjectDocument> projectDocuments, List<Task> tasks) {
        this._id = _id;
        this.featureName = featureName;
        this.featureDesc = featureDesc;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.projectDocuments = projectDocuments;
        this.tasks = tasks;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDesc() {
        return featureDesc;
    }

    public void setFeatureDesc(String featureDesc) {
        this.featureDesc = featureDesc;
    }

    public List<ProjectDocument> getProjectDocuments() {
        return projectDocuments;
    }

    public void setProjectDocuments(List<ProjectDocument> projectDocuments) {
        this.projectDocuments = projectDocuments;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }
}
