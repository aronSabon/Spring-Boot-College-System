package appSoft.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import appSoft.project.model.Salary;
import appSoft.project.service.SalaryService;

@RestController
public class SalaryRestController {

	@Autowired   
	private SalaryService ss;  
	


	
	@GetMapping("/api/salary")
	public List<Salary> getAll() {
		return ss.getAllSalary();
	}
	@GetMapping("/api/salary/{id}")
	public Salary getOneEmp(@PathVariable int id) {
		return ss.getSalaryById(id);
	}
	@PostMapping("/api/salary")
	public String add(@RequestBody Salary salary) {
		ss.addSalary(salary);
		return "success";
	}
	@DeleteMapping("/api/salary/{id}")
	public String delete(@PathVariable int id) {
		ss.deleteSalaryById(id);
		return "success";
	}
	@PutMapping("/api/salary")
	public String update(@RequestBody Salary salary) {
		ss.updateSalary(salary);
		return "Success";
	}
	
}
