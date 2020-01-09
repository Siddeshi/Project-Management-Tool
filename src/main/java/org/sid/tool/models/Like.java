package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;


@Document
public class Like implements Serializable {

    @Id
    private ObjectId _id;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private ProjectDetails projectDetails;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private UserDetail userDetail;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "_id")
    private ProductBacklog productBacklog;

    public Like() {
    }

    public Like(ObjectId _id, ProjectDetails projectDetails, UserDetail userDetail, ProductBacklog productBacklog) {
        this._id = _id;
        this.projectDetails = projectDetails;
        this.userDetail = userDetail;
        this.productBacklog = productBacklog;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }

}
