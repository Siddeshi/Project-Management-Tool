package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Task implements Serializable {

    Timeline timeline;
    @Id
    private String taskId;
    private String featureId;
    private String userId;
    private String taskName;
    private String taskDesc;

    public Task(String taskId, String featureId, String userId, String taskName, String taskDesc, Timeline timeline) {
        this.taskId = taskId;
        this.featureId = featureId;
        this.userId = userId;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.timeline = timeline;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
