package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Payment;
import appSoft.project.repository.PaymentRepository;
import appSoft.project.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentRepository pr;
		@Override
		public void addPayment(Payment payment) {
			// TODO Auto-generated method stub
			pr.save(payment);
		}

		@Override
		public List<Payment> getAllPayment() {
			// TODO Auto-generated method stub
			return pr.findAll();
		}

		@Override
		public void deletePaymentById(int id) {
			// TODO Auto-generated method stub
			pr.deleteById(id);
		}

		@Override
		public Payment getPaymentById(int id) {
			// TODO Auto-generated method stub
			return pr.findById(id).get();
		}

		@Override
		public void updatePayment(Payment payment) {
			// TODO Auto-generated method stub
			pr.save(payment);
		}

		@Override
		public List<Payment> getAllByRollNo(int rollNo) {
			// TODO Auto-generated method stub
			return pr.findByRollNo(rollNo);
		}

}
