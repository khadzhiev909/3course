package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> storageFaculty = new HashMap<>();
    private long count = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++count);
        storageFaculty.put(count, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return storageFaculty.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        storageFaculty.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty removeFaculty(long id) {
        return storageFaculty.remove(id);
    }
}
