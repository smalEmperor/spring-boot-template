package com.template.entity.mg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Document(collection = "student")
public class Student implements Serializable {
    @Id
    private String studentId;

    private String studentName;

    private Integer studentAge;

    private Double studentScore;
    
    private LocalDateTime studentBirthday;
}
