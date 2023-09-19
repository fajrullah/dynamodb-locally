package com.example.datafetcher;

import com.example.entity.Score;
import com.example.model.ScoreInput;
import com.example.repository.ScoreRepository;
import com.example.service.ScoreService;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class ScoreDataFetcher {
    @Autowired
    ScoreService scoreService;
    @DgsData(parentType = "Query", field = "scores")
    public List<Score> findAll(){
        return scoreService.findAll();
    }

    @DgsData(parentType = "Query", field = "score")
    public Score findOne(String id){
        return scoreService.findOne(id);
    }

    @DgsData(parentType = "Mutation", field = "addStudentScore")
    public Score insertStudentScore(@InputArgument("scoreInput") ScoreInput scoreInput){
        return scoreService.insertStudentScore(scoreInput);
    }

}
