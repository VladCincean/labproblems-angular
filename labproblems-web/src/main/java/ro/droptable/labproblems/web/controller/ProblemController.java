package ro.droptable.labproblems.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.core.service.ProblemService;
import ro.droptable.labproblems.web.dto.ProblemDto;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
@RestController
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDto getProblems() {
        log.trace("getProblems --- method entered");

        List<Problem> problems = problemService.findAll();

        log.trace("getProblems: problems={}", problems);

        return new ProblemDto(problems);
    }
}
