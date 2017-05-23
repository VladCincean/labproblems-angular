package ro.droptable.labproblems.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.droptable.labproblems.core.model.Student;

import java.util.List;

/**
 * Created by vlad on 11.04.2017.
 */
public interface StudentRepository extends LabProblemsRepository<Student, Long>, StudentRepositoryCustom {

    @Query("SELECT DISTINCT s FROM Student s")
    @EntityGraph(value = "studentWithProblems", type = EntityGraph.EntityGraphType.LOAD)
    List<Student> findAllWithProblemsGraph();

    @Query("SELECT DISTINCT s FROM Student s WHERE s.id = ?1")
    @EntityGraph(value = "studentWithProblems", type = EntityGraph.EntityGraphType.LOAD)
    Student findOneWithProblems(Long studentId);
}
