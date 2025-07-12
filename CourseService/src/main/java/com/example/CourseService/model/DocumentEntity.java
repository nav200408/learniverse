package com.example.CourseService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    private String documentName;
    private String documentDescribtion;
    private String filename;
    @ManyToOne
    @JoinColumn(name = "unitId")
    @JsonIgnore
    private UnitEntity unit;

    public DocumentEntity() {
    }

    public DocumentEntity(String documentName, String documentDescribtion, String filename, UnitEntity unit) {
        this.documentName = documentName;
        this.documentDescribtion = documentDescribtion;
        this.filename = filename;
        this.unit = unit;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentDescribtion() {
        return documentDescribtion;
    }

    public void setDocumentDescribtion(String documentDescribtion) {
        this.documentDescribtion = documentDescribtion;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }
}
