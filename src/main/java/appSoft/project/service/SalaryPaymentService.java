package appSoft.project.service;

import java.time.LocalDate;
import java.util.List;

import appSoft.project.model.SalaryPayment;

public interface SalaryPaymentService {
		void addPayment(SalaryPayment salaryPayment);
		List<SalaryPayment> getAllPayment();
		void deletePaymentById(int id);
		SalaryPayment getPaymentById(int id);
		void updatePayment(SalaryPayment salaryPayment);
		List<SalaryPayment> getAllByTeacherId(int id);
		
		List<SalaryPayment> getAllByDateBetween(LocalDate from, LocalDate to);
		List<SalaryPayment> deleteAllByTeacherId(int id);
}

