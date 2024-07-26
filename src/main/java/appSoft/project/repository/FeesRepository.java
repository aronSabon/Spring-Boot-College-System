package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Exam;
import appSoft.project.model.Fees;
import appSoft.project.model.Student;

public interface FeesRepository extends JpaRepository<Fees, Integer> {
	List<Fees> findByStudentId(int studentId);
	List<Fees> findByStudentIdAndStatus(int id,FeesStatus status);


}
