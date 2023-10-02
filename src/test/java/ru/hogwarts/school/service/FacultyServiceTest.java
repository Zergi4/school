package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {
    @InjectMocks
    FacultyService out;
    @Mock
    FacultyRepository repository;

    @Test
    void add() {
        Faculty faculty = new Faculty(1L, "test", "red");
        when(repository.save(faculty)).thenReturn(faculty);
        assertEquals(faculty, out.add(faculty));

    }

    @Test
    void get() {
        Faculty faculty = new Faculty(1L, "test", "red");
        when(repository.findById(1L)).thenReturn(Optional.of(faculty));
        assertEquals(faculty, out.get(1L));

    }
    @Test
    void update() {
        Faculty newFaculty = new Faculty(2L, "new2", "yello");
        when(repository.save(newFaculty)).thenReturn(newFaculty);
        assertEquals(newFaculty, out.update(2L, newFaculty));
    }

    @Test
    void getFacultiesByColor() {
        List<Faculty> list = new ArrayList<>(List.of(new Faculty(1L, "first", "red"),
                new Faculty(2L, "second", "red")));
        when(repository.findByColorIgnoreCase("red")).thenReturn(list);
        assertEquals(list, out.getByColorOrNameIgnoreCase("red"));
    }


}