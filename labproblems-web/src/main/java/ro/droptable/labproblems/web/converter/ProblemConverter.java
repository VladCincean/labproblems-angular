package ro.droptable.labproblems.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.web.dto.ProblemDto;

/**
 * Created by stefana on 5/2/2017.
 */
@Component
public class ProblemConverter extends BaseConverter<Problem, ProblemDto> {
    private static final Logger log = LoggerFactory.getLogger(ProblemConverter.class);

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
        ProblemDto problemDto = new ProblemDto(problem.getTitle(), problem.getDescription());
        problemDto.setId(problem.getId());
        return problemDto;
    }
}