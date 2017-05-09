package ro.droptable.labproblems.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.repository.StudentRepository;


import java.util.List;
import java.util.Map;

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

        Student student = Student.builder()
                .serialNumber(serialNumber)
                .name(name)
                .studentGroup(studentGroup)
                .build();
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

    @Override
    public Student findStudent(Long studentId) {
        log.trace("findStudent: studentId={}", studentId);

        Student student = studentRepository.findOne(studentId);

        log.trace("findStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public Student updateStudentGrades(Long studentId, Map<Long, Integer> grades) {
        log.trace("updateStudentGrades: studentId={}, grades={}", studentId, grades);

        Student student = studentRepository.findOne(studentId);
        student.getStudentProblems().stream()
                .forEach(sd -> sd.setGrade(grades.get(sd.getProblem().getId())));

        log.trace("updateStudentGrades: student={}", student);
        return student;
    }
}