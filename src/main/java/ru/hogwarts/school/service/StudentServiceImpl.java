package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student createStudent(Student student) {
        logger.info("was created");
        return studentRepository.save(student);
    }


    @Override
    public Student getStudentById(long id) {
        logger.info("was was getting");
        return studentRepository.getById(id);
    }

    //.orElseThrow(() -> new StudentNotFoundException("Not Found Student"))
    @Override
    public Student editStudent(Student student) {
        logger.info("Editing");
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(long id) {
        logger.info("was deleted");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> findAllStudentsByAgeBetween(int max, int min) {
        logger.info("find");
        return studentRepository.findByAgeBetween(max, min);
    }

    @Override
    public Faculty findFacultyByStudent(Long id) {
        logger.info("findByStudent");
        return studentRepository.getById(id).getFaculty();
//        return getStudentById(id).getFaculty();
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("all");
        return studentRepository.findAll();
    }

    /* ---------------------------------------------------------------------------*/
    @Override
    public Long getAmountStudent() {
        logger.info("getAmount");
        return studentRepository.getNumberOfAllStudents();
    }

    @Override
    public Age getAverageOfStudent() {
        logger.info("getAvaregeOfStudent");
        return studentRepository.getAverageAge();
    }

    @Override
    public Collection<Student> getTopFiveStudents() {
        logger.info("getTopFive");
        return studentRepository.getTopFiveStudents();
    }

}
