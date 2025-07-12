package com.example.CourseService.dto;

public class UnitCreationRequest {
    private int courseId;
    private String unitName;
    private String unitDesciption;

    public UnitCreationRequest() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitDesciption() {
        return unitDesciption;
    }

    public void setUnitDesciption(String unitDesciption) {
        this.unitDesciption = unitDesciption;
    }
}
