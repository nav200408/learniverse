package com.example.CourseService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;


import java.util.List;

@Entity
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String courseDetail;
    private int price;
    private String courseImage;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @Where(clause = "is_delete = false")
    private List<UnitEntity> units;

    private boolean isDelete = false;
    private boolean isPublish = false;

    public CourseEntity() {
    }

    public CourseEntity(int courseId, String courseName, String courseDetail, int price, String courseImage, List<UnitEntity> units) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.price = price;
        this.courseImage = courseImage;
        this.units = units;
    }

    public CourseEntity(String courseName, String courseDetail, int price, String courseImage, List<UnitEntity> units, boolean isDelete, boolean isPublish) {
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.price = price;
        this.courseImage = courseImage;
        this.units = units;
        this.isDelete = isDelete;
        this.isPublish = isPublish;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public List<UnitEntity> getUnits() {
        return units;
    }

    public void setUnits(List<UnitEntity> units) {
        this.units = units;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }
}
