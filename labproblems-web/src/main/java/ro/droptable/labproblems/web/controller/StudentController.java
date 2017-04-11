package ro.droptable.labproblems.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.service.StudentService;
import ro.droptable.labproblems.web.dto.StudentDto;

import java.util.List;


/**
 * Created by stefana on 4/11/2017.
 */
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="students")
    public StudentDto getStudents() {
        log.trace("getDisciplines --- method entered");

        List<Student> students = studentService.findAll();

        log.trace("getStudents: students={}", students);

        return new StudentDto(students);
    }
}
