package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Exam;
import appSoft.project.model.Faculty;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.Payment;
import appSoft.project.model.Student;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByRollNo(int rollNo);
}
