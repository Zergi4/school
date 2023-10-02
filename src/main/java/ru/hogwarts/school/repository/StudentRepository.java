package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int age1, int age2);

    Long findFacultyByNameIgnoreCase(String name);

    List<Student> findByFacultyId(Long facultyId);

    List<Student> findByAge(int i);

}
