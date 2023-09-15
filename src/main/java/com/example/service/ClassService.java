package com.example.service;

import com.example.entity.Class;
import com.example.entity.Student;
import com.example.repository.ClassRepository;
import com.example.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> findAll() {
        return classRepository.findAll();
    }

    public Class findOne(String id) {
        return classRepository.findOne(id);
    }

    public Class save(Class studentClass) {
        return classRepository.save(studentClass);
    }

    public String delete(String id){
        Class classStudent = classRepository.findOne(id);
        if (classStudent != null) {
            classRepository.delete(classStudent);
            return "Delete successfully: " + id;
        } else {
            return "Item not found for id: " + id;
        }
    }

}
