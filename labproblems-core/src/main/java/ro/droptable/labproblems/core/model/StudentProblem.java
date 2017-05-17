package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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

    @Digits(integer = 6, fraction = 0, message = "student id must be integer")
    @NotNull(message = "student id not valid")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @Digits(integer = 6, fraction = 0)
    @NotNull(message = "problem id not valid")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @DecimalMin(value = "0.00", message = "grade must be between 1 and 10")
    @DecimalMax(value = "10.00", message = "grade must be between 1 and 10")
    @Column(name = "grade")
    private Integer grade;
}
