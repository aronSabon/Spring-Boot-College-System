package appSoft.project.service;

import java.time.LocalDate;
import java.util.List;

import appSoft.project.constant.FeesStatus;
import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Expense;
import appSoft.project.model.Salary;

public interface SalaryService {
		void addSalary(Salary salary);
		List<Salary> getAllSalary();
		void deleteSalaryById(int id);
		Salary getSalaryById(int id);
		void updateSalary(Salary salary);
		
		List<Salary> getAllSalaryByTeacherId(int id);
		List<Salary> getAllByTeacherIdAndStatus(int id,SalaryStatus status);
		
		List<Salary> deleteAllByTeacherId(int id);

		}
