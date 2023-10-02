package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty update(Long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }
    public ResponseEntity<Faculty>  findFacultyByNameIgnoreCase(String name){
        return ResponseEntity.ok(facultyRepository.findFacultyByNameIgnoreCase(name));
    }
    public ResponseEntity<Faculty>  findFacultyByColorIgnoreCase(String color){
        return ResponseEntity.ok(facultyRepository.findFacultyByColorIgnoreCase(color));
    }
}
