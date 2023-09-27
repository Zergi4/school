package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

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
       return studentRepository .save(student);
    }


    public void delete(Long id) {
studentRepository.deleteById(id);    }

    public List<Student> getByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

}