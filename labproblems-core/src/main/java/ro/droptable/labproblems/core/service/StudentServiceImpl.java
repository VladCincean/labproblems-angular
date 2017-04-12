package ro.droptable.labproblems.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.repository.StudentRepository;

import java.util.List;

/**
 * Created by vlad on 11.04.2017.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public List<Student> findAll() {
        log.trace("findAll");

        List<Student> students = studentRepository.findAll();

        log.trace("findAll: students={}", students);

        return students;
    }
}