package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

public interface StudentService{
    Student createStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    Student removeStudent(long id);
}
