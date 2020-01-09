package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;

@Document
public class Timeline implements Serializable {

    @Id
    private ObjectId _id;

    private Date periodFrom;

    private Date periodTo;

    //@OneToOne(mappedBy = "timeline")
    private Milestone milestone;

    //@OneToOne(mappedBy = "timeline")
    private Task task;

    public Timeline() {
    }

    public Timeline(ObjectId _id, Date periodFrom, Date periodTo, Milestone milestone, Task task) {
        this._id = _id;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.milestone = milestone;
        this.task = task;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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