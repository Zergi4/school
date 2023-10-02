package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty get(@PathVariable("id") Long id) {
        return facultyService.get(id);
    }
    @GetMapping
    public List<Faculty> getByColor(@RequestParam("color") String color) {
        return facultyService.getByColor(color);
    }
    @GetMapping("/search") // GET localhost:8080/faculties/search?color=red or GET localhost:8080/faculties/search?name=Иван
    public ResponseEntity<Faculty> findFacultyByNameOrColorIgnoreCase(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
       if(name != null) {
           return ResponseEntity.ok(facultyService.findFacultyByNameIgnoreCase(name).getBody());
       }
       if(color != null) {
           return ResponseEntity.ok(facultyService.findFacultyByColorIgnoreCase(color).getBody());
       }
       return ResponseEntity.badRequest().build();
            }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping("{id}")
    public Faculty update(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        facultyService.delete(id);
    }


}