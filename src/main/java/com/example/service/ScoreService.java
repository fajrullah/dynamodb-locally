package com.example.service;

import com.example.entity.Class;
import com.example.entity.Score;
import com.example.entity.Student;
import com.example.model.ScoreInput;
import com.example.repository.ClassRepository;
import com.example.repository.ScoreRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;
    public List<Score> findAll(){
        return scoreRepository.findAll();
    }

    public Score findOne(String id){
        return scoreRepository.findOne(id);
    }


    public Score insertStudentScore(ScoreInput scoreInput){
        Score score = new Score();
        Double value = scoreInput.getValue();
        String classId = scoreInput.getClassId();
        String studentId = scoreInput.getStudentId();
        Class findClass = classRepository.findOne(classId);
        if(findClass == null){
            throw new IllegalArgumentException("Invalid class id: " + classId);
        }

        Student findStudent = studentRepository.findOne(studentId);
        if(findStudent == null){
            throw new IllegalArgumentException("Invalid student id: " + studentId);
        }

        score.setStudentId(studentId);
        score.setClassId(classId);
        score.setValue(value);
        return scoreRepository.insertStudentScore(score);
    }
}
