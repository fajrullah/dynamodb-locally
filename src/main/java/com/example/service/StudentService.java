package com.example.service;

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

    public String update(String id, Student student){
        return studentRepository.update(id, student);
    }

    public String delete(String id){
        return studentRepository.delete(id);
    }
}
