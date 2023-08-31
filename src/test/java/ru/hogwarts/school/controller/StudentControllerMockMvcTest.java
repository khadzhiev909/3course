package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentServiceImpl;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private StudentServiceImpl studentService;

    @MockBean
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testFindStudentById() throws Exception {
        Student student = new Student(1L, "name", 34);

        when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void testCreateStudent() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("id", 1L);
        studentObject.put("name", "name");
        studentObject.put("age", 34);

        Student student = new Student();
        student.setName("name");
        student.setAge(34);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.age").value(34));
    }
    @Test
    public void testFindStudentsByAgeBetween() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("id", 1L);
        studentObject.put("name", "name");
        studentObject.put("age", 34);

        Student student = new Student();
        student.setName("name");
        student.setAge(34);

        JSONObject studentObject1 = new JSONObject();
        studentObject.put("id", 2L);
        studentObject.put("name", "bob");
        studentObject.put("age", 24);

        Student student1 = new Student();
        student.setName("bob");
        student.setAge(24);


        when(studentRepository.save(any(Student.class))).thenReturn(student, student1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/byAgeBetween?max=35&min=23")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFacultyByStudent() throws Exception {

        Faculty faculty = new Faculty("name", "red");
        faculty.setId(1L);

        Student student = new Student(1L, "name", 34);
        student.setFaculty(faculty);

        when(studentRepository.getById(any(Long.class))).thenReturn(student);



        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/byStudent/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/student/delete/1"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1"))
                .andExpect(status().isNotFound());
    }
}
