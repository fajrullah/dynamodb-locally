package com.example.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.entity.Class;
import com.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassRepository {
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ClassRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public List<Class> findAll(){
        return dynamoDBMapper.scan(Class.class, new DynamoDBScanExpression());
    }

    public Class findOne(String id){ return dynamoDBMapper.load(Class.class, id);}

    public Class save(Class classStudent){
        dynamoDBMapper.save(classStudent);
        return classStudent;
    }

    public Class delete(Class classStudent){
        dynamoDBMapper.delete(classStudent);
        return classStudent;
    }

}
