package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stefana on 5/9/2017.
 */

@Entity
@Table(name = "student_discipline")
@IdClass(StudentProblemPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class StudentProblem implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "grade")
    private Integer grade;
}
