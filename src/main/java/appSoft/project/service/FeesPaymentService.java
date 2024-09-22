package appSoft.project.service;

import java.time.LocalDate;
import java.util.List;

import appSoft.project.model.FeesPayment;

public interface FeesPaymentService {
		void addPayment(FeesPayment feesPayment);
		List<FeesPayment> getAllPayment();
		void deletePaymentById(int id);
		FeesPayment getPaymentById(int id);
		void updatePayment(FeesPayment feesPayment);
		List<FeesPayment> getAllByRollNo(int rollNo);
		
		List<FeesPayment> getAllByDateBetween(LocalDate from, LocalDate to);
		List<FeesPayment> getAllByDateBetweenAndGrade(LocalDate from, LocalDate to,String grade);

		
		List<FeesPayment> deleteAllByRollNo(int rollNo);
}
