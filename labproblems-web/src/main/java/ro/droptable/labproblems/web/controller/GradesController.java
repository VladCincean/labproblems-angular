package ro.droptable.labproblems.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.model.StudentProblem;
import ro.droptable.labproblems.core.service.StudentService;
import ro.droptable.labproblems.web.converter.StudentProblemConverter;
import ro.droptable.labproblems.web.dto.StudentProblemDto;
import ro.droptable.labproblems.web.dto.StudentProblemsDto;

import java.util.Map;
import java.util.Set;

/**
 * Created by stefana on 5/9/2017.
 */
@RestController
public class GradesController {
    private static final Logger log = LoggerFactory.getLogger(GradesController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentProblemConverter studentProblemConverter;

    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.GET)
    public StudentProblemsDto getStudentProblems(
            @PathVariable final Long studentId) {
        log.trace("getStudentProblems: studentId={}", studentId);

        Student student = studentService.findStudent(studentId);

        Set<StudentProblem> studentProblems = student.getStudentProblems();
        log.trace("getStudentProblems: problems={}", studentProblems);
        Set<StudentProblemDto> studentProblemDtos = studentProblemConverter
                .convertModelsToDtos(studentProblems);


        StudentProblemsDto result = new StudentProblemsDto(studentProblemDtos);

        log.trace("getStudentProblems: result={}", result);

        return result;
    }

    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.PUT)
    public StudentProblemsDto updateStudentGrades(
            @PathVariable final Long studentId,
            @RequestBody final StudentProblemsDto studentProblemsDto) {
        log.trace("updateStudentGrades: studentId={}, studentProblemsDto={}",
                studentId, studentProblemsDto);

        Map<Long, Integer> grades = studentProblemConverter.convertDtoToMap(studentProblemsDto);
        Student student = studentService.updateStudentGrades(studentId, grades);

        Set<StudentProblemDto> studentProblemDtos = studentProblemConverter.
                convertModelsToDtos(student.getStudentProblems());
        StudentProblemsDto result = new StudentProblemsDto(studentProblemDtos);

        log.trace("getStudentProblems: result={}", result);

        return result;
    }
}

