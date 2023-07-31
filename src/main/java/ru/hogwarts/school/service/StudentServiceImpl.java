package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{
    private final Map<Long, Student> storageStudents = new HashMap<>();
    private long count = 0;

    @Override
    public Student createStudent(Student student) {
        student.setId(count++);
        storageStudents.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(long id) {
        return storageStudents.get(id);
    }

    @Override
    public Student editStudent(long id, Student student) {
        storageStudents.put(student.getId(), student);
        return student;
    }

    @Override
    public Student removeStudent(long id) {
        return storageStudents.remove(id);
    }
}
