package ro.droptable.labproblems.web.dto;

import lombok.*;
import ro.droptable.labproblems.core.model.Problem;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProblemDto extends BaseDto {
    private String title;
    private String description;

    @Override
    public String toString() {
        return "ProblemDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}' + super.toString();
    }
}

