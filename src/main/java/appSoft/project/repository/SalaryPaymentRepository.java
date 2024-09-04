package appSoft.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Exam;
import appSoft.project.model.Expense;
import appSoft.project.model.Faculty;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.SalaryPayment;
import appSoft.project.model.FeesPayment;
import appSoft.project.model.Student;
import jakarta.transaction.Transactional;

public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Integer> {
	List<SalaryPayment> findByTeacherId(String teacherId);
	List<SalaryPayment> findAllByDateBetween(LocalDate from, LocalDate to);
	
	@Transactional
	List<SalaryPayment> deleteAllByTeacherId(String teacherId);


}
