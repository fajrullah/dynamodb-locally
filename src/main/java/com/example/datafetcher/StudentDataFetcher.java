package com.example.datafetcher;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@DgsComponent
public class StudentDataFetcher {
    @Autowired
    StudentService studentService;

    @DgsData(parentType = "Query", field = "students")
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @DgsData(parentType = "Query", field = "student")
    public Student findOneStudent(@InputArgument("id") String id) {
        return studentService.findOne(id); // Replace with your logic to find a single student by ID
    }

}
