package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student get(@PathVariable("id") Long id) {
        return studentService.get(id);
    }
    @GetMapping
    public List<Student> getByAge(@RequestParam("age") int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/between")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam("age1") int age1, @RequestParam("age2") int age2) {
        return ResponseEntity.ok(studentService.findByAgeBetween(age1, age2).getBody());
    }
    @GetMapping("/ff")
    public ResponseEntity<Long> findFacultyByNameIgnoreCase(@RequestParam("name") String name){
        return ResponseEntity.ok(studentService.findFacultyByNameIgnoreCase(name).getBody());
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("{id}")
    public Student update(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }


}