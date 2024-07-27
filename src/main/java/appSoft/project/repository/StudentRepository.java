package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Faculty;
import appSoft.project.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findById(int id);
	List<Student> findByGradeAndFaculty(String grade,Faculty faculty);

Student findByRollNo(int rollNo);
}
