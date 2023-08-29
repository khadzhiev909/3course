package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeptions.StudentNotFoundException;
import ru.hogwarts.school.model.Age;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Not Found"));
    }

    //.orElseThrow(() -> new StudentNotFoundException("Not Found Student"))

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> findAllStudentsByAgeBetween(int max, int min) {
        return studentRepository.findByAgeBetween(max, min);
    }

    @Override
    public Faculty findFacultyByStudent(Long id) {
        return getStudentById(id).getFaculty();
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }


    /* ---------------------------------------------------------------------------*/
    @Override
    public Long getAmountStudent() {
        return studentRepository.getNumberOfAllStudents();
    }

    @Override
    public Age getAverageOfStudent() {
        return studentRepository.getAverageAge();
    }

    @Override
    public Collection<Student> getTopFiveStudents() {
        return studentRepository.getTopFiveStudents();
    }

}
