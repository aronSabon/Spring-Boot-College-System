package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.constant.FeesStatus;
import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Salary;
import appSoft.project.repository.SalaryRepository;
import appSoft.project.service.SalaryService;


@Service
public class SalaryServiceImpl implements SalaryService {
	@Autowired
	SalaryRepository sr;
		@Override
		public void addSalary(Salary salary) {
			// TODO Auto-generated method stub
			sr.save(salary);
		}

		@Override
		public List<Salary> getAllSalary() {
			// TODO Auto-generated method stub
			return sr.findAll();
		}

		@Override
		public void deleteSalaryById(int id) {
			// TODO Auto-generated method stub
			sr.deleteById(id);
		}

		@Override
		public Salary getSalaryById(int id) {
			// TODO Auto-generated method stub
			return sr.findById(id).get();
		}

		@Override
		public void updateSalary(Salary salary) {
			// TODO Auto-generated method stub
			sr.save(salary);
		}

		@Override
		public List<Salary> getAllSalaryByTeacherId(int id) {
			// TODO Auto-generated method stub
			return sr.findAllByTeacherId(id);
		}

		@Override
		public List<Salary> getAllByTeacherIdAndStatus(int id, SalaryStatus status) {
			// TODO Auto-generated method stub
			return sr.findByTeacherIdAndStatus(id, status);
		}

		@Override
		public List<Salary> deleteAllByTeacherId(int id) {
			// TODO Auto-generated method stub
			return sr.deleteAllByTeacherId(id);
		}

}
