package ro.droptable.labproblems.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.service.StudentService;
import ro.droptable.labproblems.web.dto.StudentDto;

import java.util.List;


/**
 * Created by stefana on 4/11/2017.
 */
@RestController
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudents() {
        log.trace("getProblems --- method entered");

        List<Student> students = studentService.findAll();

        log.trace("getStudents: students={}", students);

        return new StudentDto(students);
    }
}
