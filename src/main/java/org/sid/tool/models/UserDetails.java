package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class UserDetails implements Serializable {

    @Id
    private String _id;

    private String userName;

    public UserDetails(String _id, String userName) {
        this._id = _id;
        this.userName = userName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
