package ro.droptable.labproblems.core.repository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.model.StudentProblem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.criteria.*;
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

        log.trace("findAllWithProblemsJpql: method entered");

        javax.persistence.Query query = getEntityManager().createQuery("select distinct s from Student s" +
                " left join fetch s.studentProblems sp" +
                " left join fetch sp.problem p");

        List<Student> students = query.getResultList();

        log.trace("findAllWithProblemsJpql: students={}", students);
        return students;
    }

    @Override
    @Transactional
    public List<Student> findAllWithProblemsJpaCriteria() {

        log.trace("findAllWithProblemsJpaCriteria: method entered");

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);

        query.distinct(Boolean.TRUE);

        Root<Student> from = query.from(Student.class);

        Fetch<Student, StudentProblem> studentProblemFetch = from.fetch(Student_.studentProblems, JoinType.LEFT);
        studentProblemFetch.fetch(StudentProblem_.discipline, JoinType.LEFT);

        List<Student> students = getEntityManager().createQuery(query).getResultList();

        log.trace("findAllWithProblemsJpaCriteria: students={}", students);

        return students;
    }
}
