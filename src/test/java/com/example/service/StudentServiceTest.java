package com.example.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.example.builder.StudentBuilder;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
public class StudentServiceTest {

    private Student student;
    @Inject
    private StudentService studentService;

    private final String MOCKID = "1";
    private final String MOCKNAME = "Jon";
    private final String MOCKCLASSNUMBER = "A100023";

    @BeforeEach
    public void init(){
        student = new Student();
        student.setId(MOCKID);
        student.setName(MOCKNAME);
        student.setClassNumber(MOCKCLASSNUMBER);
        studentService.save(student);
    }
    @Test
    public void shouldReturnSuccess(){
        Student existStudent = studentService.findOne(student.getId());
        assertEquals(MOCKID, student.getId());
    }

    @Test
    public void shouldReturnName(){
        Student existStudent = studentService.findOne(student.getId());
        assertEquals(MOCKNAME, student.getName());
    }

    @Test
    public void shouldReturnClassNumber(){
        Student existStudent = studentService.findOne(student.getId());
        assertEquals(MOCKCLASSNUMBER, student.getClassNumber());
    }

}


