package ro.droptable.labproblems.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by vlad on 01.05.2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
