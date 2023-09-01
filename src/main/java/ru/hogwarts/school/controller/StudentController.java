package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Age;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.StudentServiceImpl;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("byAgeBetween")
    public ResponseEntity<Collection<Student>> findStudentsByAgeBetween(@RequestParam int max, @RequestParam int min) {
        return ResponseEntity.ok(studentService.findAllStudentsByAgeBetween(max, min));
    }

    @GetMapping("byStudent/{id}")
    public ResponseEntity<Faculty> findFacultyByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findFacultyByStudent(id));
    }
    /* ------------------------------------------------------------- */

    @GetMapping("/get-amount-student")
    public Long getAmountStudent() {
        return studentService.getAmountStudent();
    }

    @GetMapping("/get-average-student")
    public Age getAvgStudent() {
        return studentService.getAverageOfStudent();
    }

    @GetMapping("/students/top-five")
    public Collection<Student> getTopFiveStudents() {
        return studentService.getTopFiveStudents();
    }

}
