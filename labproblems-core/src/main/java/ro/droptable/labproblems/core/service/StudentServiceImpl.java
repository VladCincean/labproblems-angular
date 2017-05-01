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

    @Override
    @Transactional
    public Student createStudent(String serialNumber, String name, Integer studentGroup) {
        log.trace("createStudent: serialNumber={}, name={}, studentGroup={}",
                serialNumber, name, studentGroup);

        Student student = new Student(serialNumber, name, studentGroup);
        student = studentRepository.save(student);

        log.trace("createStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Long studentId, String serialNumber, String name, Integer studentGroup) {
        log.trace("updateStudent: studentId={}, serialNumber={}, name={}, studentGroup={}",
                studentId, serialNumber, name, studentGroup);

        Student student = studentRepository.findOne(studentId);
        student.setSerialNumber(serialNumber);
        student.setName(name);
        student.setStudentGroup(studentGroup);

        log.trace("updateStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        log.trace("deleteStudent: studentId={}", studentId);

        studentRepository.delete(studentId);

        log.trace("deleteStudent - method end");
    }
}