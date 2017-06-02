package ro.droptable.labproblems.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.repository.ProblemRepository;
import ro.droptable.labproblems.core.repository.StudentRepository;


import java.util.*;

/**
 * Created by vlad on 11.04.2017.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
//    @Transactional
    public List<Student> findAll() {
        log.trace("findAll");

//        List<Student> students = studentRepository.findAll();
        List<Student> students = studentRepository.findAllWithProblemsGraph();
//        List<Student> students = studentRepository.findAllWithProblemsSqlQuery();
//        List<Student> students = studentRepository.findAllWithProblemsJpql();
//        List<Student> students = studentRepository.findAllWithProblemsJpaCriteria();
        log.trace("findAll: students={}", students);

        return students;
    }

    @Override
    public Student findStudent(Long studentId) {
        log.trace("findStudent: studentId={}", studentId);

//        Student student = studentRepository.findOne(studentId);
        Student student = studentRepository.findOneWithProblems(studentId);

        log.trace("findStudent: student={}", student);

        return student;
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
                .studentProblems(new HashSet<>())
                .build();
        student = studentRepository.save(student);

        log.trace("createStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Long studentId, String serialNumber, String name, Integer studentGroup, Set<Long> problems) {
        log.trace("updateStudent: studentId={}, serialNumber={}, name={}, studentGroup={}",
                studentId, serialNumber, name, studentGroup);

        Student student = studentRepository.findOne(studentId);
        student.setSerialNumber(serialNumber);
        student.setName(name);
        student.setStudentGroup(studentGroup);

        List<Long> toRemove = new ArrayList<>();

        student.getProblems().stream()
                .map(d -> d.getId())
                .forEach(id -> {
                    if (problems.contains(id)) {
                        problems.remove(id);
                    } else {
                        toRemove.add(id);
                    }
                });

        problemRepository.findAll(toRemove)
                .forEach(p -> p.getStudentProblems().removeIf(sp -> sp.getProblem().getId().equals(p.getId())));

        List<Problem> problemList = problemRepository.findAll(problems);
        problemList.forEach(student::addProblem);
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
    @Transactional
    public Student updateStudentGrades(Long studentId, Map<Long, Integer> grades) {
        log.trace("updateStudentGrades: studentId={}, grades={}", studentId, grades);

        Student student = studentRepository.findOne(studentId);
        student.getStudentProblems()
                .forEach(sp -> sp.setGrade(grades.get(sp.getProblem().getId())));

        log.trace("updateStudentGrades: student={}", student);
        return student;
    }

    @Override
//    @Transactional
    public List<Student> filterStudents(String name) {
        log.trace("filterStudents: name={}", name);

        List<Student> students = studentRepository.findAll();
        students.removeIf(student -> !student.getName().contains(name));

        log.trace("filterStudents: students={}", students);

        return students;
    }
}