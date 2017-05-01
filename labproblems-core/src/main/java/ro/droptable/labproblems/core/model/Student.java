package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by vlad on 11.04.2017.
 *
 * Class that represents a 'real-world' {@code Student}
 */
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Student extends BaseEntity<Long> implements Serializable {

    private static final int SERIAL_NUMBER_LENGTH = 16;

    @Column(name = "serial_number", nullable = false, length = SERIAL_NUMBER_LENGTH)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "student_group", nullable = false)
    private Integer studentGroup;

    @Override
    public String toString() {
        return "Student{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", studentGroup=" + studentGroup +
                '}' + super.toString();
    }

//    public String toCsv() {
//        return getId() + "," + serialNumber + "," + name + "," + studentGroup;
//    }
}