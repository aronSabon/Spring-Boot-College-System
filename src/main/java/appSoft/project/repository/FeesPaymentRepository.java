package appSoft.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Exam;
import appSoft.project.model.Expense;
import appSoft.project.model.Faculty;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.FeesPayment;
import appSoft.project.model.Student;
import jakarta.transaction.Transactional;

public interface FeesPaymentRepository extends JpaRepository<FeesPayment, Integer> {
	List<FeesPayment> findByRollNo(int rollNo);
	List<FeesPayment> findAllByDateBetween(LocalDate from, LocalDate to);
	List<FeesPayment> findAllByDateBetweenAndGrade(LocalDate from, LocalDate to,String grade);

	
@Transactional
	List<FeesPayment> deleteAllByRollNo(int rollNo);
}
