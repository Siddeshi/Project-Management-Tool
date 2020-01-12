package org.sid.tool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class ProjectDocument implements Serializable {

    @Id
    private ObjectId _id;

    private String docName;

    private String docType;

    private byte[] document;

    public ProjectDocument() {
    }

    public ProjectDocument(ObjectId _id, String docName, String docType, byte[] document) {
        this._id = _id;
        this.docName = docName;
        this.docType = docType;
        this.document = document;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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
