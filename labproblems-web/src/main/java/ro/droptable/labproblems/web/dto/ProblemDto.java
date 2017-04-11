package ro.droptable.labproblems.web.dto;

import ro.droptable.labproblems.core.model.Problem;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
public class ProblemDto {
    private List<Problem> problems;

    public ProblemDto() {
    }

    public ProblemDto(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}

