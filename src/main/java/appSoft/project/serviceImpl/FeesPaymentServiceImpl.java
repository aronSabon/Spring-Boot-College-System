package appSoft.project.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.FeesPayment;
import appSoft.project.repository.FeesPaymentRepository;
import appSoft.project.service.FeesPaymentService;


@Service
public class FeesPaymentServiceImpl implements FeesPaymentService {
	@Autowired
	FeesPaymentRepository pr;
		@Override
		public void addPayment(FeesPayment feesPayment) {
			// TODO Auto-generated method stub
			pr.save(feesPayment);
		}

		@Override
		public List<FeesPayment> getAllPayment() {
			// TODO Auto-generated method stub
			return pr.findAll();
		}

		@Override
		public void deletePaymentById(int id) {
			// TODO Auto-generated method stub
			pr.deleteById(id);
		}

		@Override
		public FeesPayment getPaymentById(int id) {
			// TODO Auto-generated method stub
			return pr.findById(id).get();
		}

		@Override
		public void updatePayment(FeesPayment feesPayment) {
			// TODO Auto-generated method stub
			pr.save(feesPayment);
		}

		@Override
		public List<FeesPayment> getAllByRollNo(int rollNo) {
			// TODO Auto-generated method stub
			return pr.findByRollNo(rollNo);
		}

		@Override
		public List<FeesPayment> getAllByDateBetween(LocalDate from, LocalDate to) {
			// TODO Auto-generated method stub
			return pr.findAllByDateBetween(from, to);
		}

		@Override
		public List<FeesPayment> deleteAllByRollNo(int rollNo) {
			// TODO Auto-generated method stub
			return pr.deleteAllByRollNo(rollNo);
		}

}
