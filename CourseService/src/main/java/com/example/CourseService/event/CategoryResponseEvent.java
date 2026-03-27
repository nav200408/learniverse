package com.example.CourseService.event;

public class CategoryResponseEvent {
    private int courseId;
    private boolean success;
    private String message;

    public CategoryResponseEvent() {
    }

    public CategoryResponseEvent(int courseId, boolean success, String message) {
        this.courseId = courseId;
        this.success = success;
        this.message = message;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
