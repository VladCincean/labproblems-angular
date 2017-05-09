package ro.droptable.labproblems.core.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by stefana on 5/9/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class StudentProblemPK implements Serializable {
    private Student student;
    private Problem problem;
}
