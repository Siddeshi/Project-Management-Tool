package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class ProductBacklog implements Serializable {

    @Id
    private String featureId;

    private String projectId;

    private String milestoneId;

    private String teamId;

    private String featureName;

    private String featureDesc;

    private List<ProjectDocument> projectDocuments;

    private List<Task> tasks;

    public ProductBacklog(String featureId, String projectId, String milestoneId, String teamId,
                          String featureName, String featureDesc, List<ProjectDocument> projectDocuments, List<Task> tasks) {
        this.featureId = featureId;
        this.projectId = projectId;
        this.milestoneId = milestoneId;
        this.teamId = teamId;
        this.featureName = featureName;
        this.featureDesc = featureDesc;
        this.projectDocuments = projectDocuments;
        this.tasks = tasks;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
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
}
