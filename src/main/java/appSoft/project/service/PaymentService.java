package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Payment;

public interface PaymentService {
		void addPayment(Payment payment);
		List<Payment> getAllPayment();
		void deletePaymentById(int id);
		Payment getPaymentById(int id);
		void updatePayment(Payment payment);
		List<Payment> getAllByRollNo(int rollNo);
}
