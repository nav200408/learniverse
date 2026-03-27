package com.example.CategoryService.event;

public class CategoryEvent {
    private String category;
    private int courseId;
    private String type;

    public CategoryEvent() {
    }

    public CategoryEvent(String category, int courseId, String type) {
        this.category = category;
        this.courseId = courseId;
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
