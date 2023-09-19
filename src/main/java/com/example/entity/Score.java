package com.example.entity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Score")
public class Score {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;


    @DynamoDBAttribute
    private double value;

    @DynamoDBAttribute
    private String studentId;

    @DynamoDBAttribute
    private String classId;

    public String getStudentId() {
        return studentId;
    }

    public String getClassId() {
        return classId;
    }


    public double getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
