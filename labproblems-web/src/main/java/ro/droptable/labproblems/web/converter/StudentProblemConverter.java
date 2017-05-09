package ro.droptable.labproblems.web.converter;

import org.springframework.stereotype.Component;
import ro.droptable.labproblems.core.model.StudentProblem;
import ro.droptable.labproblems.web.dto.StudentProblemDto;
import ro.droptable.labproblems.web.dto.StudentProblemsDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefana on 5/9/2017.
 */
@Component
public class StudentProblemConverter extends BaseConverterGeneric<StudentProblem, StudentProblemDto> {
    @Override
    public StudentProblem convertDtoToModel(StudentProblemDto studentProblemDto) {
        throw new RuntimeException("not yet implemented.");
    }

    @Override
    public StudentProblemDto convertModelToDto(StudentProblem studentProblem) {
        StudentProblemDto studentProblemDto = StudentProblemDto.builder()
                .studentId(studentProblem.getStudent().getId())
                .problemId(studentProblem.getProblem().getId())
                .problemTitle(studentProblem.getProblem().getTitle())
                .grade(studentProblem.getGrade())
                .build();
        return studentProblemDto;
    }

    public Map<Long, Integer> convertDtoToMap(StudentProblemsDto studentProblemsDto) {
        Map<Long, Integer> grades = new HashMap<>();
        studentProblemsDto.getStudentProblems().stream()
                .forEach(sd -> grades.put(sd.getProblemId(), sd.getGrade()));
        return grades;
    }
}
