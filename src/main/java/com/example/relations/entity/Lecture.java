package com.example.relations.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String day;
    private Integer start_time;
    private Integer end_time;

    @ManyToOne
//    @JoinColumn(name = "instructor")
    private Instructor instructor;

    @ManyToMany(mappedBy = "attending")
    private List<Student> students;
}
