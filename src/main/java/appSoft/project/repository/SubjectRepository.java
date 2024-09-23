package appSoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Faculty;
import appSoft.project.model.Subject;
import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {
 List<Subject> findByGrade(String grade);
 List<Subject> findByFacultyAndGrade(Faculty faculty, String grade);
}
