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
public class RegisterInput {
    private String studentId;
    private String classId;

    public String getStudentId() {
        return this.studentId;
    }

    public String getClassId() {
        return this.classId;
    }
}
