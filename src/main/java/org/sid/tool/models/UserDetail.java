package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

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
    //private Team team;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDetail", cascade = CascadeType.ALL)
    //private List<Comment> comments;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDetail", cascade = CascadeType.ALL)
    //private List<Like> likes;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDetail", cascade = CascadeType.ALL)
    //private List<Task> tasks;

    public UserDetail() {
    }

    public UserDetail(ObjectId _id, String userName, String designation, long phone, String email,
                      String address) {
        this._id = _id;
        this.userName = userName;
        this.designation = designation;
        this.phone = phone;
        this.email = email;
        this.address = address;
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
}
