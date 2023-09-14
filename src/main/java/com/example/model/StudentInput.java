package com.example.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentInput {
    private String classNumber;
    private String name;
}
