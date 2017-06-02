package ro.droptable.labproblems.core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Ignore;
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
 * Created by vlad on 02/06/2017.
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
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void findAll() throws Exception {
        List<Student> students = studentService.findAll();
        Assert.assertEquals("there should be four students", 4, students.size());
    }

    @Test
    public void updateStudent() throws Exception {
        studentService.updateStudent(1L, "aaa", "bbb", 999, new HashSet<>());
        Student student = studentService.findStudent(1L);
        Assert.assertEquals("the serial number should be aaa", "aaa", student.getSerialNumber());
        Assert.assertEquals("the number should be bbb", "bbb", student.getName());
        Assert.assertEquals("the student group should be 999", Integer.valueOf(999), student.getStudentGroup());
    }

    @Test // this one fails at studentService.createStudent(...)
    public void createStudent() throws Exception {
        studentService.createStudent("aaa", "bbb", 999);
        List<Student> students = studentService.findAll();
        Assert.assertEquals("there should be five students", 5, students.size());
    }

    @Test
    public void deleteStudent() throws Exception {
        studentService.deleteStudent(2L);
        List<Student> students = studentService.findAll();
        Assert.assertEquals("there should be three students", 3, students.size());
        Student student = studentService.findStudent(2L);
        Assert.assertNull("it should be null", student);
    }
}
