package com.example.service;

import com.example.entity.Class;
import com.example.entity.Student;
import com.example.model.ClassInput;
import com.example.repository.ClassRepository;
import com.example.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;
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
    public List<Class> getAllClassesWithStudents() {
        List<Class> classes = classRepository.findAll();

        for (Class classEntity : classes) {
            Set<String> studentIds = classEntity.getStudentIds();
            List<Student> students = new ArrayList<>();
            for(String studentId : studentIds) {
                Student studentEntity = studentRepository.findOne(studentId);
                if(studentEntity != null){
                    students.add(studentEntity);
                }
            }
            classEntity.setStudents(students);
        }

        return classes;
    }

}
