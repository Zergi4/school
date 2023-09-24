package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facs = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facs.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return facs.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        facs.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id) {
        return facs.remove(id);

    }

}
