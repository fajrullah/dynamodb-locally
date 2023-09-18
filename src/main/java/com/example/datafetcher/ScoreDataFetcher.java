package com.example.datafetcher;

import com.example.entity.Score;
import com.example.repository.ScoreRepository;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class ScoreDataFetcher {
    @Autowired
    ScoreRepository scoreRepository;
    @DgsData(parentType = "Query", field = "scores")
    public List<Score> findAll(){
        return scoreRepository.findAll();
    }

    @DgsData(parentType = "Query", field = "score")
    public Score findOne(String id){
        return scoreRepository.findOne(id);
    }
}
