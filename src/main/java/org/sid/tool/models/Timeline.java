package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;

@Document
public class Timeline implements Serializable {

    @Id
    private String timelineId;

    private String milestoneId;

    private String taskId;

    private Date periodFrom;

    private Date periodTo;

    private Milestone milestone;

    private Task task;

    public Timeline(String timelineId, String milestoneId, String taskId, Date periodFrom,
                    Date periodTo, Milestone milestone, Task task) {
        this.timelineId = timelineId;
        this.milestoneId = milestoneId;
        this.taskId = taskId;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.milestone = milestone;
        this.task = task;
    }

    public String getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(String timelineId) {
        this.timelineId = timelineId;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
