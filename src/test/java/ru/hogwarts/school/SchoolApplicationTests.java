package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

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
    void delete()  throws Exception {
       restTemplate.delete("http://localhost:" + port + "/students/2");
       Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/2", Student.class)).isNull();
    }
}
