package ro.droptable.labproblems.core.repository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.Student;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by vlad on 23.05.2017.
 */
public class StudentRepositoryImpl
        extends CustomRepositorySupport<Student, Long>
        implements StudentRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    @Override
    @Transactional
    public List<Student> findAllWithProblemsSqlQuery() {
        log.trace("findAllWithProblemsSqlQuery: method entered");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        Query query = session.createSQLQuery("SELECT DISTINCT {s.*}, {sp.*}, {p.*}" +
                " FROM student s" +
                " LEFT JOIN student_problem sp ON sp.student_id = s.id" +
                " LEFT JOIN problem p ON p.id = sp.problem_id")
                .addEntity("s", Student.class)
                .addJoin("sp", "s.studentProblems")
                .addJoin("p", "sp.problem")
                .addEntity("s", Student.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Student> students = query.list();

        log.trace("findAllWithProblemsSqlQuery: students={}", students);
        return students;
    }

    @Override
    @Transactional
    public List<Student> findAllWithProblemsJpql() {
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public List<Student> findAllWithProblemsJpaCriteria() {
        throw new NotImplementedException();
    }
}
