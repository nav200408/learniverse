package com.example.CourseService.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentCreationRequest {
    private int unitId;
    private String documentName;
    private String documentDescribtion;
    private MultipartFile filename;

    public DocumentCreationRequest() {
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
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

    public MultipartFile getFilename() {
        return filename;
    }

    public void setFilename(MultipartFile filename) {
        this.filename = filename;
    }
}
