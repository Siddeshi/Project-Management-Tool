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

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<ProjectDocument> projectDocuments;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<Task> tasks;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private ProjectDetails projectDetails;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private Team team;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private Milestone milestone;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public ProductBacklog() {
    }

    public ProductBacklog(ObjectId _id, String featureName, String featureDesc, List<ProjectDocument> projectDocuments,
                          List<Task> tasks, ProjectDetails projectDetails, Team team, Milestone milestone, List<Comment> comments) {
        this._id = _id;
        this.featureName = featureName;
        this.featureDesc = featureDesc;
        this.projectDocuments = projectDocuments;
        this.tasks = tasks;
        this.projectDetails = projectDetails;
        this.team = team;
        this.milestone = milestone;
        this.comments = comments;
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

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
