package com.example.datafetcher;

import com.example.entity.Class;
import com.example.entity.Score;
import com.example.entity.Student;
import com.example.model.ClassInput;
import com.example.model.RegisterInput;
import com.example.model.StudentInput;
import com.example.repository.ClassRepository;
import com.example.repository.StudentRepository;
import com.example.service.ClassService;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class StudentDataFetcher {
    @Autowired
    StudentService studentService;

    @Autowired
    ClassService classService;

    @DgsData(parentType = "Query", field = "students")
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @DgsData(parentType = "Query", field = "allStudentsWithClasses")
    public List<Student> getAllStudentsWithClasses() {
        return studentService.getAllStudentsWithClasses();
    }

    @DgsData(parentType = "Query", field = "student")
    public Student findOneStudent(@InputArgument("id") String id) {
        return studentService.findOne(id);
    }

    @DgsData(parentType = "Mutation", field = "student")
    public Student updateOrSaveStudent(@InputArgument("studentInput") Student student) {
        studentService.save(student);
        return student;
    }

    @DgsData(parentType = "Mutation", field = "createStudent")
    public Student save(@InputArgument("studentInput") StudentInput studentInput) {
        return studentService.saveStudent(studentInput);
    }

    @DgsData(parentType = "Mutation", field = "deleteStudent")
    public String deleteStudent(@InputArgument("id") String id) {
        try{
            studentService.delete(id);
            return id;
        } catch(Exception e) {
            return null;
        }
    }

    @DgsData(parentType = "Mutation", field = "studentRegisterClass")
    public Student studentRegisterClass(@InputArgument("registerInput") RegisterInput registerInput) {
        String studentId = registerInput.getStudentId();
        String classId = registerInput.getClassId();

        Student student = studentService.findOne(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Invalid studentId: " + studentId);
        }

        Class classEntity = classService.findOne(classId);
        if (classEntity == null) {
            throw new IllegalArgumentException("Invalid classId: " + classId);
        }

        if (student.getClassIds().contains(classId)) {
            throw new IllegalArgumentException("Student is already registered for class: " + classId);
        }

        student.getClassIds().add(classId);
        studentService.save(student);
        return studentService.getStudentWithClasses(studentId);
    }


    @DgsData(parentType = "Query", field = "stundentScores")
    public Student getStudentScores(String id){
        return studentService.getStudentScores(id);
    }

}
