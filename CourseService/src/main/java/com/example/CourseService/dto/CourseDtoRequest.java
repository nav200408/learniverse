package com.example.CourseService.dto;

import org.springframework.web.multipart.MultipartFile;

public class CourseDtoRequest {
    private int courseId;
    private String courseName;
    private String courseDetail;
    private int price;
    private MultipartFile courseImage;

    public CourseDtoRequest() {
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

    public MultipartFile getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(MultipartFile courseImage) {
        this.courseImage = courseImage;
    }
}
