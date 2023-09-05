package com.example.service;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findOne(String id) {
        return studentRepository.findOne(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public String update(Student student){
        Student result = studentRepository.findOne(student.getId());
        if (result != null) {
            return studentRepository.update(student.getId(), student);
        } else {
            return student.getId();
        }
    }


    public String delete(String id){
        Student student = studentRepository.findOne(id);
        if (student != null) {
            studentRepository.delete(student);
            return "Delete successfully: " + id;
        } else {
            return "Item not found for id: " + id;
        }
    }
}
