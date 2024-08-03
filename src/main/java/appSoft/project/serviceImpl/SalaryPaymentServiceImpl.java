package appSoft.project.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.SalaryPayment;
import appSoft.project.repository.SalaryPaymentRepository;
import appSoft.project.service.SalaryPaymentService;


@Service
public class SalaryPaymentServiceImpl implements SalaryPaymentService {
	@Autowired
	SalaryPaymentRepository salaryPaymentRepository;
		@Override
		public void addPayment(SalaryPayment salaryPayment) {
			// TODO Auto-generated method stub
			salaryPaymentRepository.save(salaryPayment);
		}

		@Override
		public List<SalaryPayment> getAllPayment() {
			// TODO Auto-generated method stub
			return salaryPaymentRepository.findAll();
		}

		@Override
		public void deletePaymentById(int id) {
			// TODO Auto-generated method stub
			salaryPaymentRepository.deleteById(id);
		}

		@Override
		public SalaryPayment getPaymentById(int id) {
			// TODO Auto-generated method stub
			return salaryPaymentRepository.findById(id).get();
		}

		@Override
		public void updatePayment(SalaryPayment salaryPayment) {
			// TODO Auto-generated method stub
			salaryPaymentRepository.save(salaryPayment);
		}

		@Override
		public List<SalaryPayment> getAllByTeacherId(String teacherId) {
			// TODO Auto-generated method stub
			return salaryPaymentRepository.findByTeacherId(teacherId);
		}

		@Override
		public List<SalaryPayment> getAllByDateBetween(LocalDate from, LocalDate to) {
			// TODO Auto-generated method stub
			return salaryPaymentRepository.findAllByDateBetween(from, to);
		}

		@Override
		public List<SalaryPayment> deleteAllByTeacherId(String teacherId) {
			// TODO Auto-generated method stub
			return salaryPaymentRepository.deleteAllByTeacherId(teacherId);
		}

}
