package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private final StudentService studentService;

    @GetMapping("{id}") // GET http://localhost:8080/student/1
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student studentById = studentService.findStudentById(id);
        if (studentById == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(studentById);
    }

    @GetMapping     // GET http://localhost:8080/student
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());

    }

    @PostMapping // POST http://localhost:8080/student
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);

    }

    @PutMapping // PUT http://localhost:8080/student
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student student1 = studentService.editStudent(student);
        if (student1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/1
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);

    }

}
