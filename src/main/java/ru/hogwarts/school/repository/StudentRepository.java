package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Age;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int max, int min);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long getNumberOfAllStudents();

    @Query(value = "SELECT AVG(age) AS age FROM student", nativeQuery = true)
    Age getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY id desc limit 5", nativeQuery = true)
    Collection<Student> getTopFiveStudents();
}
