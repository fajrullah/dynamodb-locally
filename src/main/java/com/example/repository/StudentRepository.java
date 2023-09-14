package com.example.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.entity.Student;
import com.example.model.StudentDto;
import com.example.model.StudentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public StudentRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Student> findAll(){
        return dynamoDBMapper.scan(Student.class, new DynamoDBScanExpression());
    }
    public Student findOne(String id){ return dynamoDBMapper.load(Student.class, id);}

    public Student save(Student student){
        dynamoDBMapper.save(student);
        return student;
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
