package com.example.CourseService.dto;

public class EditUnitDTO {
    private int unitId;
    private String unitName;
    private String unitDesciption;

    public EditUnitDTO() {
    }

    public EditUnitDTO(int unitId, String unitName, String unitDesciption) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.unitDesciption = unitDesciption;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
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
