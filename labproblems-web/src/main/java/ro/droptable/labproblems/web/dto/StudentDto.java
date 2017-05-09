package ro.droptable.labproblems.web.dto;

import lombok.*;

import java.util.Set;

/**
 * Created by stefana on 4/11/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDto extends BaseDto {
    private String serialNumber;
    private String name;
    private Integer studentGroup;
    private Set<Long> problems;

    @Override
    public String toString() {
        return "StudentDto{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", studentGroup=" + studentGroup +
                ", problems=" + problems +
                '}' + super.toString();
    }
}
