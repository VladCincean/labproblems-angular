package ro.droptable.labproblems.core.service;

import ro.droptable.labproblems.core.model.Student;

import java.util.List;

/**
 * Created by vlad on 11.04.2017.
 */
public interface StudentService {
    List<Student> findAll();
}