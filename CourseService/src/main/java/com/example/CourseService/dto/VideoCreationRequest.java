package com.example.CourseService.dto;

import org.springframework.web.multipart.MultipartFile;

public class VideoCreationRequest {
    private String videoName;
    private String description;
    private MultipartFile filename;
    private int unitId;

    public VideoCreationRequest() {
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFilename() {
        return filename;
    }

    public void setFilename(MultipartFile filename) {
        this.filename = filename;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
