package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Document
public class ProjectDetails implements Serializable {

    @Id
    private ObjectId _id;

    private String projectName;

    private String projectDesc;

    private Date projectStartDate;

    private Date projectEndDate;

    private String projectStatus;

    private long likesCount;

    private long commentsCount;

    private List<Team> teams;

    private List<Milestone> milestones;

    private List<ProjectDocument> projectDocuments;

    public ProjectDetails() {
    }

    public ProjectDetails(ObjectId _id, String projectName, String projectDesc, Date projectStartDate,
                          Date projectEndDate, String projectStatus, long likesCount, long commentsCount,
                          List<Team> teams, List<Milestone> milestones, List<ProjectDocument> projectDocuments) {
        this._id = _id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectStatus = projectStatus;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.teams = teams;
        this.milestones = milestones;
        this.projectDocuments = projectDocuments;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public List<ProjectDocument> getProjectDocuments() {
        return projectDocuments;
    }

    public void setProjectDocuments(List<ProjectDocument> projectDocuments) {
        this.projectDocuments = projectDocuments;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }
}
