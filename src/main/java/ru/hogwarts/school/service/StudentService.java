package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Age;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

public interface StudentService{
    Student createStudent(Student student);

    Student getStudentById(long id);

    Student editStudent(Student student);

    void removeStudent(long id);

    Collection<Student> findAllStudentsByAgeBetween(int max, int min);

    Faculty findFacultyByStudent(Long id);

    Collection<Student> getAll();

    /* ---------------------------------------------------------------------------*/
    Long getAmountStudent();

    Age getAverageOfStudent();

    Collection<Student> getTopFiveStudents();

    List<String> getStudentNamesStartWithA();

    OptionalDouble getAverageAge();
}