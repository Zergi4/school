package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();

    private Long currentId = 1L;


    public Faculty add(Faculty faculty) {
        Long id = ++currentId;
        faculty.setId(id);
        faculties.put(id, faculty);

        return faculties.get(id);
    }


    public Faculty get(Long id) {
        return faculties.get(id);
    }

    public Faculty update(Long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            Faculty facultyById = faculties.get(id);
            facultyById.setName(facultyById.getName());
            facultyById.setColor(facultyById.getColor());
            faculties.put(id, facultyById);
            return faculties.get(id);
        } else {
            return null;
        }
    }


    public void delete(Long id) {
        faculties.remove(id);
    }

    public List<Faculty> getByColor(String color) {
        return faculties.values()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
