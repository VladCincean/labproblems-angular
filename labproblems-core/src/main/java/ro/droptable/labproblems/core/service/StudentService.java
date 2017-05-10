package ro.droptable.labproblems.core.service;

import ro.droptable.labproblems.core.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vlad on 11.04.2017.
 */
public interface StudentService {
    List<Student> findAll();

    Student createStudent(String serialNumber, String name, Integer studentGroup);

    Student updateStudent(Long studentId, String serialNumber, String name, Integer studentGroup, Set<Long> problems);

    void deleteStudent(Long studentId);

    Student findStudent(Long studentId);

    Student updateStudentGrades(Long studentId, Map<Long, Integer> grades);

    List<Student> filterStudents(String name);
}