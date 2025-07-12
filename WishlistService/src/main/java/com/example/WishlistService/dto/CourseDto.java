package com.example.WishlistService.dto;

public class CourseDto {
    private int courseId;
    private String courseName;
    private String courseDetail;
    private int price;
    private String courseImage;

    public CourseDto() {
    }

    public CourseDto(String courseName, String courseDetail, int price, String courseImage, int courseId) {
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.price = price;
        this.courseImage = courseImage;
        this.courseId = courseId;
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
}
