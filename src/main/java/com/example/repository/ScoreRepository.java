package com.example.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.xspec.L;
import com.example.entity.Class;
import com.example.entity.Score;
import com.example.model.ClassInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreRepository {
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ScoreRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Score> findAll(){
        return dynamoDBMapper.scan(Score.class, new DynamoDBScanExpression());
    }

    public Score findOne(String id){
        return dynamoDBMapper.load(Score.class, id);
    }

//    public Score saveClass(ClassInput classStudent){
//
//    }
//
//    public Score save(Score score){
//        dynamoDBMapper.save(score);
//        return score;
//    }
//
//    public Score delete(Class score){
//        dynamoDBMapper.delete(classStudent);
//        return classStudent;
//    }

}
