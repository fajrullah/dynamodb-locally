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
public class ClassInput {
    private String className;

    private Set<String> studentIds = new HashSet<>();
}
