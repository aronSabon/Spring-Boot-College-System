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


import appSoft.project.model.Subject;
import appSoft.project.service.SubjectService;

@RestController
public class SubjectRestController {

	@Autowired   
	private SubjectService ss;  
	


	
	@GetMapping("/api/address")
	public List<Subject> getAll() {
		return ss.getAllSubject();
	}
	@GetMapping("/api/address/{id}")
	public Subject getOneEmp(@PathVariable int id) {
		return ss.getSubjectById(id);
	}
	@PostMapping("/api/address")
	public String add(@RequestBody Subject subject) {
		ss.addSubject(subject);
		return "success";
	}
	@DeleteMapping("/api/address/{id}")
	public String delete(@PathVariable int id) {
		ss.deleteSubjectById(id);
		return "success";
	}
	@PutMapping("/api/address")
	public String update(@RequestBody Subject subject) {
		ss.updateSubject(subject);
		return "Success";
	}
	
}
