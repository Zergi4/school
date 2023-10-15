package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private FacultyController facultyController;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void get() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1", Student.class))
                .isNotNull();
    }

    @Test
    void getByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students?age=21", Collection.class))
                .isNotNull();
    }

    @Test
    void findByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/beetween?age1=21&age2=23", String.class))
                .isNotNull();
    }


    @Test
    void add() throws Exception {
        Student student = new Student();
        student.setName("Bob");
        student.setAge(21);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull()
                .contains("Bob");
    }

    @Test
    void delete() throws Exception {
        restTemplate.delete("http://localhost:" + port + "/students/2");
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/2", Student.class)).isNull();
    }
    @Test
    void contextLoadsFaculty() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    void getFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/1", Faculty.class))
                .isNotNull();

    }

    @Test
    void getByColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties?color=red", Collection.class))
                .isNotNull();
    }

    @Test
    void getByColorOrNameIgnoreCase() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties//by-name-or-color?color=red", Faculty.class))
                .isNotNull();
    }

    @Test
    void getStudentsByFacultyId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/students-by-faculty-id?id=1", List.class))
                .isNotNull();
    }

    @Test
    void addFaculty() throws Exception {

            Faculty faculty = new Faculty(1L, "test", "red");

            Assertions
                    .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", faculty, Faculty.class))
                    .isNotNull()
                    .hasFieldOrPropertyWithValue("name", "test");
        }


    @Test
    void updateFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "test2", "red2");
        restTemplate.put("http://localhost:" + port + "/faculties/1", faculty);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/1", Faculty.class))
                .isNotNull().hasFieldOrPropertyWithValue("name", "test2");}
    @Test
    void deleteFaculty() throws Exception {
        restTemplate.delete("http://localhost:" + port + "/faculties/10");
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/10", Faculty.class)).isNull();
    }
}
