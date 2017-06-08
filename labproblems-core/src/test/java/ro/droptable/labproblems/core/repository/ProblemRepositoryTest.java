package ro.droptable.labproblems.core.repository;

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

import java.util.List;

/**
 * Created by vlad on 08/06/2017.
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
public class ProblemRepositoryTest {
    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void findAll() throws Exception {
        List<Problem> problems = problemRepository.findAll();
        Assert.assertEquals("there should be 2 problems", 2, problems.size());
    }

    @Test
    public void save() throws Exception {
        Problem problem = Problem.builder()
                .title(".....")
                .description("..................")
                .build();
        problemRepository.save(problem);
        List<Problem> problems = problemRepository.findAll();
        System.out.println(problems);
        Assert.assertEquals("there should be 3 problems", 3, problems.size());
    }
}
