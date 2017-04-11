package ro.droptable.labproblems.web.dto;

import ro.droptable.labproblems.core.model.Student;

import java.util.List;

/**
 * Created by stefana on 4/11/2017.
 */
public class StudentDto {
    private List<Student> students;

    public StudentDto() {
    }

    public StudentDto(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
