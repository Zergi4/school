package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student add(Student student) {
        return studentRepository.save(student);
    }


    public Student get(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public Student update(Long id, Student student) {
        return studentRepository.save(student);
    }


    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Collection<Student>> findByAgeBetween(int age1, int age2) {
        return ResponseEntity.ok(studentRepository.findByAgeBetween(age1, age2));
    }

    public ResponseEntity<Long> findFacultyByNameIgnoreCase(String name) {
        return ResponseEntity.ok(studentRepository.findFacultyByNameIgnoreCase(name));
    }

    public Faculty getFacultyByStudentId(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }
    public List<Student> getByFacultyId(Long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }
}