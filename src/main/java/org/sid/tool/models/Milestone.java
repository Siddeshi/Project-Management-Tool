package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class Milestone implements Serializable {

    @Id
    private String milestoneId;

    private String projectId;

    private String milestoneName;

    private String milestoneDesc;

    private Timeline timeline;

    private List<ProductBacklog> productBacklogs;

    public Milestone(String milestoneId, String projectId, String milestoneName, String milestoneDesc,
                     Timeline timeline, List<ProductBacklog> productBacklogs) {
        this.milestoneId = milestoneId;
        this.projectId = projectId;
        this.milestoneName = milestoneName;
        this.milestoneDesc = milestoneDesc;
        this.timeline = timeline;
        this.productBacklogs = productBacklogs;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
}
