package ro.droptable.labproblems.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by stefana on 5/2/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProblemsDto {
    private Set<ProblemDto> problems;
}
