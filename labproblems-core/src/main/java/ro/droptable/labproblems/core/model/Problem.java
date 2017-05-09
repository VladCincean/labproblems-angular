package ro.droptable.labproblems.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by stefana on 4/11/2017.
 */
@Entity
@Table(name = "problem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Problem extends BaseEntity<Long> implements Serializable {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<StudentProblem> studentProblems = new HashSet<>();

    public Problem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Problem(Long id, String title, String description) {
        this.setId(id);
        this.title = title;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) &&
                Objects.equals(this.title, ((Problem)o).title) &&
                Objects.equals(this.description, ((Problem)o).description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result +  title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(
                studentProblems.stream()
                        .map(sd -> sd.getStudent())
                        .collect(Collectors.toSet())
        );
    }

    public void addStudent(Student student) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setStudent(student);
        studentProblem.setProblem(this);
        studentProblems.add(studentProblem);
    }

    public void addGrade(Student student, Integer grade) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setStudent(student);
        studentProblem.setGrade(grade);
        studentProblem.setProblem(this);
        studentProblems.add(studentProblem);
    }
}
