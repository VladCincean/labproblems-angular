package ro.droptable.labproblems.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.web.dto.StudentDto;

import java.util.stream.Collectors;

/**
 * Created by vlad on 01.05.2017.
 */
@Component
public class StudentConverter extends BaseConverter<Student, StudentDto> {
    private static final Logger log = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public StudentDto convertModelToDto(Student student) {

        StudentDto studentDto = StudentDto.builder()
                .serialNumber(student.getSerialNumber())
                .name(student.getName())
                .studentGroup(student.getStudentGroup())
                .build();
        studentDto.setId(student.getId());

        studentDto.setProblems(student.getProblems().stream()
                .map(d -> d.getId())
                .collect(Collectors.toSet()));
        return studentDto;
    }
}
