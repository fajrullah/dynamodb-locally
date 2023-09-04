package com.example.builder;

import com.example.entity.Student;

public class StudentBuilder {
    public static Student buildDefaultStudentData(
            String id,
            String name,
            String classNumber
    ) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setClassNumber(classNumber);
        return student;
    }
}
