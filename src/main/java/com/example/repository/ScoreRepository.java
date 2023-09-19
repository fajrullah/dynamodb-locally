package com.example.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.xspec.L;
import com.example.entity.Class;
import com.example.entity.Score;
import com.example.model.ClassInput;
import com.example.model.ScoreInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public Score findOneStudentScore(String studentId, String classId){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("studentId = :studentId AND classId = :classId")
                .withExpressionAttributeValues(Map.of(
                        ":studentId", new AttributeValue().withS(studentId),
                        ":classId", new AttributeValue().withS(classId)
                ));
        List<Score> scores = dynamoDBMapper.scan(Score.class, scanExpression);
        if (!scores.isEmpty()) {
            return scores.get(0);
        } else {
            return null;
        }
    }

    public Score insertStudentScore(Score score){
        dynamoDBMapper.save(score);
        return score;
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
