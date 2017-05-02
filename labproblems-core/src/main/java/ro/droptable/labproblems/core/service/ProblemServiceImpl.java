package ro.droptable.labproblems.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.core.repository.ProblemRepository;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
@Service
public class ProblemServiceImpl implements ProblemService{
    private static final Logger log = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    @Transactional
    public List<Problem> findAll() {
        log.trace("findAll");

        List<Problem> problems = problemRepository.findAll();

        log.trace("findAll: problems={}", problems);

        return problems;
    }

    @Override
    @Transactional
    public Problem createProblem(String title, String description) {
        log.trace("createProblem: title={}, description={}",
                title, description);

        Problem problem = new Problem(title, description);
        problem = problemRepository.save(problem);

        log.trace("createProblem: problem={}", problem);

        return problem;
    }

    @Override
    @Transactional
    public Problem updateProblem(Long problemId, String title, String description) {
        log.trace("updateProblem: problemId={}, title={}, description={}",
                problemId, title, description);

        Problem problem = problemRepository.findOne(problemId);
        problem.setTitle(title);
        problem.setDescription(description);
        log.trace("updateProblem: problem={}", problem);

        return problem;
    }

    @Override
    @Transactional
    public void deleteProblem(Long problemId) {
        log.trace("deleteProblem: problemId={}", problemId);

        problemRepository.delete(problemId);

        log.trace("deleteProblem - method end");
    }
}
