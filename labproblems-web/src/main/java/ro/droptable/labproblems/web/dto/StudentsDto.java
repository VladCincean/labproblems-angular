package ro.droptable.labproblems.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by vlad on 01.05.2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsDto {
    private Set<StudentDto> students;
}
