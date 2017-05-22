package ro.droptable.labproblems.core.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by stefana on 5/9/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode
public class StudentProblemPK implements Serializable {
    private Student student;
    private Problem problem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProblemPK that = (StudentProblemPK) o;
        return student.equals(that.student) && problem.equals(that.problem);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + problem.hashCode();
        return result;
    }
}
