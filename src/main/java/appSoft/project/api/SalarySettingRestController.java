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


import appSoft.project.model.SalarySetting;
import appSoft.project.model.Student;
import appSoft.project.repository.SalarySettingRepository;
import appSoft.project.service.SalarySettingService;

@RestController
public class SalarySettingRestController {

	@Autowired   
	private SalarySettingService salarySettingService;  
	
	@Autowired   
	private SalarySettingRepository salarySettingRepository; 


	
	@GetMapping("/api/salarySetting")
	public List<SalarySetting> getAll() {
		return salarySettingService.getAllSalarySetting();
	}
	@GetMapping("/api/salarySetting/{id}")
	public SalarySetting getOneEmp(@PathVariable int id) {
		return salarySettingService.getSalarySettingById(id);
	}
	@PostMapping("/api/salarySetting")
	public String add(@RequestBody SalarySetting salarySetting) {
		salarySettingService.addSalarySetting(salarySetting);
		return "success";
	}
	@PostMapping("/api/salarySetting/list")
	public String addList(@RequestBody SalarySetting[] salarySetting) {
		salarySettingRepository.saveAll(List.of(salarySetting));
		
		return "success";
	}
	@DeleteMapping("/api/salarySetting/{id}")
	public String delete(@PathVariable int id) {
		salarySettingService.deleteSalarySettingById(id);
		return "success";
	}
	@PutMapping("/api/salarySetting")
	public String update(@RequestBody SalarySetting salarySetting) {
		salarySettingService.updateSalarySetting(salarySetting);
		return "Success";
	}
	
}
