package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> storageStudents = new HashMap<>();
    private long count = 0;

    public Student createStudent(Student student) {
        storageStudents.put(count++ ,student);
        return student;
    }

    public Student findStudent(long id) {
        return storageStudents.get(id);
    }

    public Student editStudent(Student student) {
        storageStudents.put(student.getId(), student);
        return student;
    }

    public Student removeStudent(long id) {
        return storageStudents.remove(id);
    }
}
