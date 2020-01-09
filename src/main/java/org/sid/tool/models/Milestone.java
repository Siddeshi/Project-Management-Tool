package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class Milestone implements Serializable {

    @Id
    private ObjectId _id;

    private String milestoneName;

    private String milestoneDesc;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(unique = true)
    private Timeline timeline;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "milestone", cascade = CascadeType.ALL)
    private List<ProductBacklog> productBacklogs;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private ProjectDetails projectDetails;

    public Milestone() {
    }

    public Milestone(ObjectId _id, String milestoneName, String milestoneDesc, Timeline timeline,
                     List<ProductBacklog> productBacklogs, ProjectDetails projectDetails) {
        this._id = _id;
        this.milestoneName = milestoneName;
        this.milestoneDesc = milestoneDesc;
        this.timeline = timeline;
        this.productBacklogs = productBacklogs;
        this.projectDetails = projectDetails;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMilestoneDesc() {
        return milestoneDesc;
    }

    public void setMilestoneDesc(String milestoneDesc) {
        this.milestoneDesc = milestoneDesc;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public List<ProductBacklog> getProductBacklogs() {
        return productBacklogs;
    }

    public void setProductBacklogs(List<ProductBacklog> productBacklogs) {
        this.productBacklogs = productBacklogs;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }
}
