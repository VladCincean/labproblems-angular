package ro.droptable.labproblems.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vlad on 11.04.2017.
 *
 * Class that represents a 'real-world' {@code Student}
 */
@Entity
@Table(name = "student")
public class Student extends BaseEntity<Long> implements Serializable {

    private static final int SERIAL_NUMBER_LENGTH = 16;

    @Column(name = "serial_number", nullable = false, length = SERIAL_NUMBER_LENGTH)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "student_group", nullable = false)
    private Integer studentGroup;

//    private static long currentId = 1;

    public Student() {
    }

    public Student(String serialNumber, String name, Integer studentGroup) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.studentGroup = studentGroup;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(Integer studentGroup) {
        this.studentGroup = studentGroup;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) &&
                Objects.equals(this.serialNumber, ((Student)o).serialNumber);
    }

    @Override
    public int hashCode() {
        return serialNumber.hashCode();
    }

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