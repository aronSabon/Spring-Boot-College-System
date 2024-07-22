package appSoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Exam;
import appSoft.project.model.Student;

public interface ExamRepository extends JpaRepository<Exam, Integer> {


}
