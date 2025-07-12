package com.example.CourseService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitId;
    private String unitName;
    private String Description;
    @ManyToOne
    @JoinColumn(name = "courseId")
    @JsonIgnore
    private CourseEntity course;
    @OneToMany(mappedBy = "unit",cascade = CascadeType.ALL)
    private List<VideoEntity> video;
    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<DocumentEntity> documents;

    private boolean isDelete = false;

    public UnitEntity() {
    }

    public UnitEntity(String unitName, String description, CourseEntity course, List<VideoEntity> video, List<DocumentEntity> documents, boolean isDelete) {
        this.unitName = unitName;
        Description = description;
        this.course = course;
        this.video = video;
        this.documents = documents;
        this.isDelete = isDelete;
    }

    public UnitEntity(String unitName, String description, CourseEntity course, List<VideoEntity> video, List<DocumentEntity> documents) {
        this.unitName = unitName;
        Description = description;
        this.course = course;
        this.video = video;
        this.documents = documents;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public List<VideoEntity> getVideo() {
        return video;
    }

    public void setVideo(List<VideoEntity> video) {
        this.video = video;
    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
