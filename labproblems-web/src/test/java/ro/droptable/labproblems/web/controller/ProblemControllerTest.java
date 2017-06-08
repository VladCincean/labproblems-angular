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
import ro.droptable.labproblems.core.model.Problem;
import ro.droptable.labproblems.core.service.ProblemService;
import ro.droptable.labproblems.web.converter.ProblemConverter;
import ro.droptable.labproblems.web.dto.ProblemDto;

import java.util.*;

/**
 * Created by vlad on 08/06/2017.
 */
public class ProblemControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ProblemController problemController;

    @Mock
    private ProblemService problemService;

    @Mock
    private ProblemConverter problemConverter;

    private Problem problem1;
    private Problem problem2;
    private ProblemDto problemDto1;
    private ProblemDto problemDto2;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(problemController)
                .build();

        problem1 = Problem.builder().title("sn1").description("s1").build();
        problem1.setId(1L);
        problem2 = Problem.builder().title("sn2").description("s2").build();
        problem2.setId(2L);

        problemDto1 = createProblemDto(problem1);
        problemDto2 = createProblemDto(problem2);
    }

    private ProblemDto createProblemDto(Problem problem) {
        ProblemDto problemDto = ProblemDto.builder()
                .title(problem.getTitle())
                .description(problem.getDescription())
                .build();
        problemDto.setId(problem.getId());
        return problemDto;
    }

    @Test
    public void getProblems() throws Exception {
        List<Problem> problems = Arrays.asList(problem1, problem2);
        Set<ProblemDto> problemDtos = new HashSet<>(Arrays.asList(problemDto1, problemDto2));

        Mockito.when(problemService.findAll())
                .thenReturn(problems);
        Mockito.when(problemConverter.convertModelsToDtos(problems))
                .thenReturn(problemDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/problems"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problems", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problems[0].description",
                        CoreMatchers.anyOf(Is.is("s1"), Is.is("s2"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problems[1].title",
                        CoreMatchers.anyOf(Is.is("sn1"), Is.is("sn2"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println(result);

        Mockito.verify(problemService, Mockito.times(1))
                .findAll();
        Mockito.verify(problemConverter, Mockito.times(1))
                .convertModelsToDtos(problems);
        Mockito.verifyNoMoreInteractions(problemService, problemConverter);
    }

    @Test
    public void updateProblem() throws Exception {
        Mockito.when(problemService.updateProblem(
                problem1.getId(),
                problemDto1.getTitle(),
                problemDto1.getDescription()
        )).thenReturn(problem1);

        Mockito.when(problemConverter.convertModelToDto(problem1))
                .thenReturn(problemDto1);

        Map<String, ProblemDto> problemDtoMap = new HashMap<>();
        problemDtoMap.put("problem", problemDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.put(
                        "/problems/{problemId}",
                        problem1.getId(),
                        problemDtoMap
                ).contentType(MediaType.APPLICATION_JSON_UTF8).content(toJsonString(problemDtoMap)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problem.description", Is.is("s1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problem.title", Is.is("sn1")));

        Mockito.verify(problemService, Mockito.times(1))
                .updateProblem(
                        problem1.getId(),
                        problemDto1.getTitle(),
                        problemDto1.getDescription()
                );
        Mockito.verify(problemConverter, Mockito.times(1))
                .convertModelToDto(problem1);
        Mockito.verifyNoMoreInteractions(problemService, problemConverter);
    }

    private String toJsonString(Map<String, ProblemDto> problemDtoMap) {
        try {
            return new ObjectMapper().writeValueAsString(problemDtoMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createProblem() throws Exception {
        Mockito.when(problemService.createProblem(
                problemDto1.getTitle(),
                problemDto1.getDescription()
        )).thenReturn(problem1);

        Mockito.when(problemConverter.convertModelToDto(problem1))
                .thenReturn(problemDto1);

        Map<String, ProblemDto> problemDtoMap = new HashMap<>();
        problemDtoMap.put("problem", problemDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post(
                        "/problems",
                        problem1.getId(),
                        problemDtoMap
                ).contentType(MediaType.APPLICATION_JSON_UTF8).content(toJsonString(problemDtoMap)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problem.description", Is.is("s1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.problem.title", Is.is("sn1")));

        Mockito.verify(problemService, Mockito.times(1))
                .createProblem(
                        problemDto1.getTitle(),
                        problemDto1.getDescription()
                );
        Mockito.verify(problemConverter, Mockito.times(1))
                .convertModelToDto(problem1);
        Mockito.verifyNoMoreInteractions(problemService, problemConverter);
    }

    @Test
    public void deleteProblem() throws Exception {
        Mockito.doNothing().when(problemService).deleteProblem(problem1.getId());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.delete(
                        "/problems/{problemId}",
                        problem1.getId()
                ).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(problemService, Mockito.times(1))
                .deleteProblem(problem1.getId());
        Mockito.verifyNoMoreInteractions(problemService);
    }
}