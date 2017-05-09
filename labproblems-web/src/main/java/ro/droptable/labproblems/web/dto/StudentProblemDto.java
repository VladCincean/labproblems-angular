package ro.droptable.labproblems.web.dto;

import lombok.*;

/**
 * Created by stefana on 5/9/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentProblemDto {
    private Long studentId;
    private Long problemId;
    private String problemTitle;
    private Integer grade;
}
