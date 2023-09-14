package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Age;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

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

    @Override
    public List<String> getStudentNamesStartWithA() {
        logger.info("getStudentNamesStartWithA");
        return studentRepository.findAll()
                .stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith("A"))
                .sorted(String::compareTo).toList();

    }

    @Override
    public OptionalDouble getAverageAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average();
    }

    @Override
    public void getAllNameOfStudents() {
        logger.info("getAllNameOfStudents");
        List<Student> names = studentRepository.findAll();

        System.out.println(names.get(0).getName());
        System.out.println(names.get(1).getName());

        Thread thread1 = new Thread(() -> {
            System.out.println(names.get(2).getName());
            System.out.println(names.get(3).getName());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(names.get(4).getName());
            System.out.println(names.get(5).getName());
        });


        thread1.start();
        thread2.start();

    }

    @Override
    public void getAllNameOfStudentsSync() {
        logger.info("getAllNameOfStudentsSync");
        List<Student> names = studentRepository.findAll();

        System.out.println(names.get(0).getName());
        System.out.println(names.get(1).getName());

        Thread thread1 = new Thread(() -> {
            sync(names.get(2));
            sync(names.get(3));
        });

        Thread thread2 = new Thread(() -> {
            sync(names.get(4));
            sync(names.get(5));
        });


        thread1.start();
        thread2.start();

    }

    private synchronized void sync(Student student) {
        System.out.println(student.getName());
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
