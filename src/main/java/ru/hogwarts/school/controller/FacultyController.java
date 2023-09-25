package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/faculty/1
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty facultyById = facultyService.findFaculty(id);
        if (facultyById == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(facultyById);
    }

    @GetMapping     // GET http://localhost:8080/faculty
    public ResponseEntity<Collection<Faculty>> getAllStudents() {
        return ResponseEntity.ok(facultyService.getAllStudents());

    }

    @PostMapping // POST http://localhost:8080/faculty
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);

    }

    @PutMapping // PUT http://localhost:8080/faculty
    public ResponseEntity<Faculty> editStudent(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.editFaculty(faculty);
        if (faculty1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/faculty/1
    public Faculty deleteFaculty (@PathVariable Long id) {
        return facultyService.deleteFaculty(id);

    }

}
