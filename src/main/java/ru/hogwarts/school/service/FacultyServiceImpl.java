package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final Map<Long, Faculty> storageFaculty = new HashMap<>();
    private long count = 0;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(count++);
        storageFaculty.put(faculty.getId(), faculty);
        return faculty;
    }
    @Override
    public Faculty findFaculty(long id) {
        return storageFaculty.get(id);
    }
    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        if (!storageFaculty.containsKey(faculty)) {
            return null;
        }
        storageFaculty.put(id, faculty);
        return faculty;
    }
    @Override
    public Faculty removeFaculty(long id) {
        return storageFaculty.remove(id);
    }
}
