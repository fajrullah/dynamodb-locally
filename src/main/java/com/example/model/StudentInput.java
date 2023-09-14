package com.example.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentInput {
    private String classNumber;
    private String name;
    private Set<String> classIds = new HashSet<>();
}
