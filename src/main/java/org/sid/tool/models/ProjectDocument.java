package org.sid.tool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class ProjectDocument implements Serializable {

    @Id
    private String docId;

    private String projectId;

    private String featureId;

    private String docName;

    private String docType;

    private byte[] document;

    public ProjectDocument(String docId, String projectId, String featureId, String docName, String docType, byte[] document) {
        this.docId = docId;
        this.projectId = projectId;
        this.featureId = featureId;
        this.docName = docName;
        this.docType = docType;
        this.document = document;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}
