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
    private String[] roles;
    private long phone;
    private String email;
    private String address;

    public UserDetail() {
    }

    public UserDetail(ObjectId _id, String userName, String[] roles, long phone,
                      String email, String address) {
        this._id = _id;
        this.userName = userName;
        this.roles = roles;
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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
