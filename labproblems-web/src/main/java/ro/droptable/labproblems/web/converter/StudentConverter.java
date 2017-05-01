package ro.droptable.labproblems.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.web.dto.StudentDto;

/**
 * Created by vlad on 01.05.2017.
 */
@Component
public class StudentConverter extends BaseConverter<Student, StudentDto> {
    private static final Logger log = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public StudentDto convertModelToDto(Student student) {
        StudentDto studentDto = new StudentDto(student.getSerialNumber(), student.getName(), student.getStudentGroup());
        studentDto.setId(student.getId());
        return studentDto;
    }
}
