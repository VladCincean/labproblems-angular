package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by stefana on 5/9/2017.
 */

@Entity
@Table(name = "student_problem")
@IdClass(StudentProblemPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode
public class StudentProblem implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "grade")
    private Integer grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProblem that = (StudentProblem) o;
        return student.equals(that.student) && problem.equals(that.problem);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + problem.hashCode();
        return result;
    }
}
