package ro.droptable.labproblems.core.service;

import ro.droptable.labproblems.core.model.Problem;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
public interface ProblemService {
    List<Problem> findAll();
}
