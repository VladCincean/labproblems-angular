package ro.droptable.labproblems.core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ro.droptable.labproblems.core.ITConfig;
import ro.droptable.labproblems.core.model.Problem;

import java.util.HashSet;
import java.util.List;

/**
 * Created by stefana on 6/5/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class ProblemServiceTest {

    @Autowired
    private ProblemService problemService;

    @Test
    public void findAll() throws Exception {
        List<Problem> problems = problemService.findAll();
        Assert.assertEquals("there should be 2 problems", 2, problems.size());
    }

    @Test
    public void updateProblem() throws Exception {
        problemService.updateProblem(10L, "problemU", "updated");
        List<Problem> problem = problemService.findAll();
        problem.removeIf(p->p.getId() != 10L);
        Problem crtProblem = problem.get(0);
        Assert.assertEquals("the title should be problemU", "problemU", crtProblem.getTitle());
        Assert.assertEquals("the description should be updated", "updated", crtProblem.getDescription());
        }

    @Test
    public void createProblem() throws Exception {
        problemService.createProblem("aaa", "bbb");
        List<Problem> problems = problemService.findAll();
        System.out.println(problems);
        Assert.assertEquals("there should be 3 problems", 3, problems.size());
    }

    @Test
    public void deleteProblem() throws Exception {
        problemService.deleteProblem(20L);
        List<Problem> problems = problemService.findAll();
        Assert.assertEquals("there should be 1 problem", 1, problems.size());
    }
}

