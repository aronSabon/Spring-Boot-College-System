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


import appSoft.project.model.Fees;
import appSoft.project.service.FeesService;

@RestController
public class FeesRestController {

	@Autowired   
	private FeesService fs;  
	


	
	@GetMapping("/api/fees")
	public List<Fees> getAll() {
		return fs.getAllFees();
	}
	@GetMapping("/api/fees/{id}")
	public Fees getOneEmp(@PathVariable int id) {
		return fs.getFeesById(id);
	}
	@PostMapping("/api/fees")
	public String add(@RequestBody Fees fees) {
		fs.addFees(fees);
		return "success";
	}
	@DeleteMapping("/api/fees/{id}")
	public String delete(@PathVariable int id) {
		fs.deleteFeesById(id);
		return "success";
	}
	@PutMapping("/api/fees")
	public String update(@RequestBody Fees fees) {
		fs.updateFees(fees);
		return "Success";
	}
	
}
