package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import appSoft.project.model.Faculty;
import appSoft.project.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findById(int id);
	List<Student> findByGradeAndFaculty(String grade,Faculty faculty);

Student findByRollNo(int rollNo);
@Query("SELECT COALESCE(MAX(s.rollNo), 0) FROM Student s")
int findMaxRollNo();
}
