package ro.droptable.labproblems.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.core.service.ProblemService;
import ro.droptable.labproblems.web.dto.ProblemDto;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value="problems")
    public ProblemDto getProblems() {
        log.trace("getDisciplines --- method entered");

        List<Problem> problems = problemService.findAll();

        log.trace("getProblems: problems={}", problems);

        return new ProblemDto(problems);
    }
}
