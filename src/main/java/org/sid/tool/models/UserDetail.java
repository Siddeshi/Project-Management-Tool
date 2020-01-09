package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document
public class UserDetail implements Serializable {


    @Id
    private ObjectId _id;
    private String userName;
    private String designation;
    private long phone;
    private String email;
    private String address;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private Team team;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDetail", cascade = CascadeType.ALL)
    private List<Comment> comments;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDetail", cascade = CascadeType.ALL)
    private List<Like> likes;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDetail", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public UserDetail() {
    }

    public UserDetail(ObjectId _id, String userName, String designation, long phone, String email,
                      String address, Team team, List<Comment> comments, List<Like> likes, List<Task> tasks) {
        this._id = _id;
        this.userName = userName;
        this.designation = designation;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.team = team;
        this.comments = comments;
        this.likes = likes;
        this.tasks = tasks;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
