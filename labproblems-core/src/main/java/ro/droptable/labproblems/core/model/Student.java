package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vlad on 11.04.2017.
 *
 * Class that represents a 'real-world' {@code Student}
 */
@Entity
@Table(name = "student")
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "studentWithProblems",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "studentProblems",
                                subgraph = "studentProblemsGraph"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "studentProblemsGraph",
                                attributeNodes = {
                                        @NamedAttributeNode(
                                                value = "problem"
                                        )
                                }
                        )
                }
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student extends BaseEntity<Long> implements Serializable {

    private static final int SERIAL_NUMBER_LENGTH = 16;

    @Column(name = "serial_number", nullable = false, length = SERIAL_NUMBER_LENGTH)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "student_group", nullable = false)
    private Integer studentGroup;

    // la @OneToMany, FetchType-ul default e LAZY; puteam sa nu scriu "fetch = FetchType.LAZY"
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<StudentProblem> studentProblems = new HashSet<>();

    @Override
    public String toString() {
        return "Student{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", studentGroup=" + studentGroup +
                '}' + super.toString();
    }

    public Set<Problem> getProblems() {
        return this.studentProblems == null ?
                new HashSet<>() :
                Collections.unmodifiableSet(
                this.studentProblems.stream().
                        map(sd -> sd.getProblem()).
                        collect(Collectors.toSet()));
    }

    public void addProblem(Problem problem) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setProblem(problem);
        studentProblem.setStudent(this);
        studentProblems.add(studentProblem);
    }

    public void addProblems(Set<Problem> problems) {
        problems.stream()
                .forEach(problem -> addProblem(problem));
    }

    public void addGrade(Problem problem, Integer grade) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setProblem(problem);
        studentProblem.setGrade(grade);
        studentProblem.setStudent(this);
        studentProblems.add(studentProblem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return serialNumber.equals(student.serialNumber);
    }

    @Override
    public int hashCode() {
        return serialNumber.hashCode();
    }

//    public String toCsv() {
//        return getId() + "," + serialNumber + "," + name + "," + studentGroup;
//    }
}