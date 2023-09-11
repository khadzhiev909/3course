package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("was created");
        return facultyRepository.save(faculty);
    }

    @Override
    public Optional<Faculty> findFaculty(long id) {
        logger.info("was was getting");
        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Editing");
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(long id) {
        logger.info("was deleted");
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty findFacultyByName(String name) {
        logger.info("findByName");
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }


    @Override
    public Collection<Faculty> findFacultyByColor(String color) {
        logger.info("findByColor");
        return facultyRepository.findAll().stream().filter(f -> f.equals(color)).collect(Collectors.toList());
//        return getAll().stream().filter(f -> f.equals(color)).collect(Collectors.toList());
    }


    @Override
    public Collection<Student> findStudentsByFaculty(Long id) {
        logger.info("findByfacultyes");
        return facultyRepository.getById(id).getStudent();
//        return findFaculty(id).get().getStudent();
    }

    @Override
    public Collection<Faculty> getAllByColor(String color) {
        logger.info("getAllById");
        return facultyRepository.findAll().stream().filter(f -> f.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
//        return getAll().stream().filter(f -> f.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
    }


}