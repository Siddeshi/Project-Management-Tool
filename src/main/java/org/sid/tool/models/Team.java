package org.sid.tool.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Document
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String teamId;

    private String teamName;

    private String teamDesc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private ProjectDetails projectDetails;

    private List<UserDetail> userDetail;

    private List<ProductBacklog> productBacklogs;

    public Team(String teamId, String teamName, String teamDesc,
                ProjectDetails projectDetails, List<UserDetail> userDetail, List<ProductBacklog> productBacklogs) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        this.projectDetails = projectDetails;
        this.userDetail = userDetail;
        this.productBacklogs = productBacklogs;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public List<ProductBacklog> getProductBacklogs() {
        return productBacklogs;
    }

    public void setProductBacklogs(List<ProductBacklog> productBacklogs) {
        this.productBacklogs = productBacklogs;
    }

    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
    }
}
