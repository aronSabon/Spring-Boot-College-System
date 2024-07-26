package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Salary;

public interface SalaryService {
		void addSalary(Salary salary);
		List<Salary> getAllSalary();
		void deleteSalaryById(int id);
		Salary getSalaryById(int id);
		void updateSalary(Salary salary);
		}
