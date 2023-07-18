package com.example.relations;

import com.example.relations.entity.Lecture;
import com.example.relations.entity.Student;
import com.example.relations.repository.LectureRepository;
import com.example.relations.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @PutMapping("{id}/lectures/{lectureId}")
    public void updateStudentLectures(
            @PathVariable("id") Long id,
            @PathVariable("lectureId") Long lectureId
    ) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Student student = optionalStudent.get();
        Lecture lecture = optionalLecture.get();

        student.getAttending().add(lecture);
        studentRepository.save(student);
    }
}
