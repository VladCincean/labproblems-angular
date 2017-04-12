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
}
