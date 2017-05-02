package ro.droptable.labproblems.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.core.service.ProblemService;
import ro.droptable.labproblems.web.converter.ProblemConverter;
import ro.droptable.labproblems.web.dto.EmptyJsonResponse;
import ro.droptable.labproblems.web.dto.ProblemDto;
import ro.droptable.labproblems.web.dto.ProblemsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stefana on 4/11/2017.
 */
@RestController
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemConverter problemConverter;

    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    public ProblemsDto getProblems() {
        log.trace("getProblems --- method entered");

        List<Problem> problems = problemService.findAll();

        log.trace("getProblems: problems={}", problems);

        return new ProblemsDto(problemConverter.convertModelsToDtos(problems));
    }

    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    public Map<String, ProblemDto> createProblem(
            @RequestBody final Map<String, ProblemDto> problemDtoMap
    ) {
        log.trace("createProblem: problemDtoMap={}", problemDtoMap);

        ProblemDto problemDto = problemDtoMap.get("problem");
        Problem problem = problemService.createProblem(
                problemDto.getTitle(),
                problemDto.getDescription()
        );

        Map<String, ProblemDto> result = new HashMap<>();
        result.put("problem", problemConverter.convertModelToDto(problem));

        log.trace("createProblem: result={}", result);

        return result;
    }

    @RequestMapping(value = "/problems/{problemId}", method = RequestMethod.PUT)
    public Map<String, ProblemDto> updateProblem(
            @PathVariable final Long problemId,
            @RequestBody final Map<String, ProblemDto> problemDtoMap
    ) {
        log.trace("updateProblem: problemId={}, problemDtoMap={}", problemId, problemDtoMap);

        ProblemDto problemDto = problemDtoMap.get("problem");
        Problem problem = problemService.updateProblem(
                problemId,
                problemDto.getTitle(),
                problemDto.getDescription()
        );

        Map<String, ProblemDto> result = new HashMap<>();
        result.put("problem", problemConverter.convertModelToDto(problem));

        log.trace("updateProblem: result={}", result);

        return result;
    }

    @RequestMapping(value = "problems/{problemId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProblem(
            @PathVariable final Long problemId
    ) {
        log.trace("deleteProblem: problemId={}", problemId);

        problemService.deleteProblem(problemId);

        log.trace("deleteProblem - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
