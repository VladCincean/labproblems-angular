package ro.droptable.labproblems.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.droptable.labproblems.core.model.Student;
import ro.droptable.labproblems.core.service.StudentService;
import ro.droptable.labproblems.web.converter.StudentConverter;
import ro.droptable.labproblems.web.dto.StudentDto;

import java.util.*;

/**
 * Created by vlad on 02/06/2017.
 */
public class StudentControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Mock
    private StudentConverter studentConverter;

    private Student student1;
    private Student student2;
    private StudentDto studentDto1;
    private StudentDto studentDto2;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .build();

        student1 = Student.builder().serialNumber("sn1").name("s1").build();
        student1.setId(1L);
        student2 = Student.builder().serialNumber("sn2").name("s2").build();
        student2.setId(2L);

        studentDto1 = createStudentDto(student1);
        studentDto2 = createStudentDto(student2);
    }

    private StudentDto createStudentDto(Student student) {
        StudentDto studentDto = StudentDto.builder()
                .serialNumber(student.getSerialNumber())
                .name(student.getName())
                .studentGroup(student.getStudentGroup())
                .build();
        studentDto.setId(student.getId());
        return studentDto;
    }

    @Test
    public void getStudents() throws Exception {
        List<Student> students = Arrays.asList(student1, student2);
        Set<StudentDto> studentDtos = new HashSet<>(Arrays.asList(studentDto1, studentDto2));

        Mockito.when(studentService.findAll())
                .thenReturn(students);
        Mockito.when(studentConverter.convertModelsToDtos(students))
                .thenReturn(studentDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students[0].name",
                        CoreMatchers.anyOf(Is.is("s1"), Is.is("s2"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students[1].serialNumber",
                        CoreMatchers.anyOf(Is.is("sn1"), Is.is("sn2"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println(result);

        Mockito.verify(studentService, Mockito.times(1))
                .findAll();
        Mockito.verify(studentConverter, Mockito.times(1))
                .convertModelsToDtos(students);
        Mockito.verifyNoMoreInteractions(studentService, studentConverter);
    }

    @Test
    public void updateStudent() throws Exception {
        Mockito.when(studentService.updateStudent(
                student1.getId(),
                studentDto1.getSerialNumber(),
                studentDto1.getName(),
                studentDto1.getStudentGroup(),
                studentDto1.getProblems()
        )).thenReturn(student1);

        Mockito.when(studentConverter.convertModelToDto(student1))
                .thenReturn(studentDto1);

        Map<String, StudentDto> studentDtoMap = new HashMap<>();
        studentDtoMap.put("student", studentDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.put(
                        "/students/{studentId}",
                        student1.getId(),
                        studentDtoMap
                ).contentType(MediaType.APPLICATION_JSON_UTF8).content(toJsonString(studentDtoMap)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.student.name", Is.is("s1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.student.serialNumber", Is.is("sn1")));

        Mockito.verify(studentService, Mockito.times(1))
                .updateStudent(
                        student1.getId(),
                        studentDto1.getSerialNumber(),
                        studentDto1.getName(),
                        studentDto1.getStudentGroup(),
                        studentDto1.getProblems()
                );
        Mockito.verify(studentConverter, Mockito.times(1))
                .convertModelToDto(student1);
        Mockito.verifyNoMoreInteractions(studentService, studentConverter);
    }

    private String toJsonString(Map<String, StudentDto> studentDtoMap) {
        try {
            return new ObjectMapper().writeValueAsString(studentDtoMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createStudent() throws Exception {
        Mockito.when(studentService.createStudent(
                studentDto1.getSerialNumber(),
                studentDto1.getName(),
                studentDto1.getStudentGroup()
        )).thenReturn(student1);

        Mockito.when(studentConverter.convertModelToDto(student1))
                .thenReturn(studentDto1);

        Map<String, StudentDto> studentDtoMap = new HashMap<>();
        studentDtoMap.put("student", studentDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post(
                        "/students",
                        student1.getId(),
                        studentDtoMap
                ).contentType(MediaType.APPLICATION_JSON_UTF8).content(toJsonString(studentDtoMap)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.student.name", Is.is("s1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.student.serialNumber", Is.is("sn1")));

        Mockito.verify(studentService, Mockito.times(1))
                .createStudent(
                        studentDto1.getSerialNumber(),
                        studentDto1.getName(),
                        studentDto1.getStudentGroup()
                );
        Mockito.verify(studentConverter, Mockito.times(1))
                .convertModelToDto(student1);
        Mockito.verifyNoMoreInteractions(studentService, studentConverter);
    }

    @Test
    public void deleteStudent() throws Exception {
        Mockito.doNothing().when(studentService).deleteStudent(student1.getId());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.delete(
                        "/students/{studentId}",
                        student1.getId()
                ).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(studentService, Mockito.times(1))
                .deleteStudent(student1.getId());
        Mockito.verifyNoMoreInteractions(studentService);
    }
}
