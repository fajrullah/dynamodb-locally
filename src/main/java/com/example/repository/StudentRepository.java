package com.example.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.entity.Class;
import com.example.entity.Student;
import com.example.model.StudentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class StudentRepository {
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public StudentRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    private Class findOneClass(String id){ return dynamoDBMapper.load(Class.class, id);}

    public List<Student> findAll(){
        return dynamoDBMapper.scan(Student.class, new DynamoDBScanExpression());
    }
    public Student findOne(String id){ return dynamoDBMapper.load(Student.class, id);}

    public Student save(Student student){
        dynamoDBMapper.save(student);
        return student;
    }

    public Student saveStudent(StudentInput student){
        Student studentEntity = new Student();
        studentEntity.setClassIds(null);
        studentEntity.setName(student.getName());
        studentEntity.setClassNumber(student.getClassNumber());
        dynamoDBMapper.save(studentEntity);
        return studentEntity;
    }

    public String update(String id, Student student) {
        dynamoDBMapper.save(student,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
        return id;
    }
    public Student delete(Student student){
        dynamoDBMapper.delete(student);
        return student;
    }
}
