SELECT s.name, s.age, f.name FROM student s
    join faculty f on s.faculty_id = f.id;

SELECT s.name, s.age, s.id FROM student s
    inner join avatar a on s.id = a.student_id;