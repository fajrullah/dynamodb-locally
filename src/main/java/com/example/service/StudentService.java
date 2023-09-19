package com.example.service;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.example.entity.Class;
import com.example.entity.Score;
import com.example.entity.Student;
import com.example.entity.StudentScore;
import com.example.model.StudentInput;
import com.example.repository.ClassRepository;
import com.example.repository.ScoreRepository;
import com.example.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findOne(String id) {
        return studentRepository.findOne(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student saveStudent(StudentInput student) {
        return studentRepository.saveStudent(student);
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

    public List<Student> getAllStudentsWithClasses() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            Set<String> classIds = student.getClassIds();
            List<Class> classes = new ArrayList<>();

            for (String classId : classIds) {
                Class classEntity = classRepository.findOne(classId);
                 if (classEntity != null) {
                    classes.add(classEntity);
                }
            }
            student.setClasses(classes);
        }

        return students;
    }

    public Student getStudentWithClasses(String id) {
        Student student = studentRepository.findOne(id);
        Set<String> classIds = student.getClassIds();
        List<Class> classes = new ArrayList<>();

        for (String classId : classIds) {
            Class classEntity = classRepository.findOne(classId);
            if (classEntity != null) {
                classes.add(classEntity);
            }
        }
        student.setClasses(classes);

        return student;
    }

    public Student getStudentScores(String id) {
        Student student = studentRepository.findOne(id);
        Set<String> classIds = student.getClassIds();
        List<StudentScore> scores = new ArrayList<>();

        for (String classId : classIds) {
            Class classEntity = classRepository.findOne(classId);
            Score scoreEntity = scoreRepository.findOneStudentScore(id, classId);
            if (classEntity != null && scoreEntity != null) {
                StudentScore score = new StudentScore();
                score.setValue(scoreEntity.getValue());
                score.setClassName(classEntity.getClassName());
                scores.add(score);
            }
        }
        student.setScores(scores);
        return student;
    }

}
