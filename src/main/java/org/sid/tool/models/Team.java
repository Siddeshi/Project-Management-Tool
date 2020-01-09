package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document
public class Team implements Serializable {

    @Id
    private ObjectId _id;

    private String teamName;

    private String teamDesc;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    //private ProjectDetails projectDetails;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.ALL)
    private List<UserDetail> userDetail;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.ALL)
    //private List<ProductBacklog> productBacklogs;

    public Team() {
    }

    public Team(ObjectId _id, String teamName, String teamDesc, List<UserDetail> userDetail) {
        this._id = _id;
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        this.userDetail = userDetail;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
    }

}
