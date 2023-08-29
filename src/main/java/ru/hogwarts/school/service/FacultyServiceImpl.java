package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Optional<Faculty> findFaculty(long id) {
        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty findFacultyByName(String name) {
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findFacultyByColor(String color) {
        return getAll().stream().filter(f -> f.equals(color)).collect(Collectors.toList());
    }


    @Override
    public Collection<Student> findStudentsByFaculty(Long id) {
        return findFaculty(id).get().getStudent();
    }

    @Override
    public Collection<Faculty> getAllByColor(String color) {
        return getAll().stream().filter(f -> f.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
    }


}