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
import ro.droptable.labproblems.core.model.Student;

import java.util.HashSet;
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
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findAll() throws Exception {
        List<Student> students = studentRepository.findAll();
        Assert.assertEquals("there should be four students", 4, students.size());
    }

    @Test
    public void save() throws Exception {
        Student student = Student.builder()
                .name("aaa")
                .serialNumber("bbb")
                .studentGroup(999)
                .studentProblems(new HashSet<>())
                .build();
        studentRepository.save(student);
        List<Student> students = studentRepository.findAll();
        System.out.println(students);
        Assert.assertEquals("there should be five students", 5, students.size());
    }
}
