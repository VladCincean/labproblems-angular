package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

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
@EqualsAndHashCode
public class StudentProblem implements Serializable {

    @NotNull(message = "student not valid")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull(message = "problem not valid")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Min(value = 0, message = "grade must be between 1 and 10")
    @Max(value = 10, message = "grade must be between 1 and 10")
    @Column(name = "grade")
    private Integer grade;
}
