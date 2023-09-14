package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto{
    private String id;
    private String classNumber;
    private String name;

    public StudentDto(String classNumber, String name) {
        this.classNumber = classNumber;
        this.name = name;
    }

}

