package ro.droptable.labproblems.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by stefana on 5/9/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentProblemsDto {
    Set<StudentProblemDto> studentProblems;
}
