package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @InjectMocks
    StudentService out;
    @Mock
    private StudentRepository repository;

    @Test
    void add() {
        Student newStudent = new Student(1L, "first", 20);
        when(repository.save(newStudent)).thenReturn(newStudent);
        assertEquals(newStudent, out.add(newStudent));
    }

    @Test
    void findStudentTrue() {
        Student newStudent = new Student(2L, "second", 20);
        when(repository.findById(2L)).thenReturn(Optional.of(newStudent));
        assertEquals(newStudent, out.get(2L));

    }


    @Test
    void editStudent() {
        Student newStudent = new Student(2L, "2nd", 20);
        when(repository.save(newStudent)).thenReturn(newStudent);
        assertEquals(newStudent, out.update(2L,newStudent));
    }



}