package com.example.model;

public class ScoreInput {
    private double value;
    private String studentId;
    private String classId;

    public double getValue() {
        return value;
    }

    public String getClassId() {
        return classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
