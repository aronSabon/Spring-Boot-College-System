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


import appSoft.project.model.Faculty;
import appSoft.project.service.FacultyService;

@RestController
public class FacultyRestController {

	@Autowired   
	private FacultyService fs;  
	


	
	@GetMapping("/api/faculty")
	public List<Faculty> getAll() {
		return fs.getAllFaculty();
	}
	@GetMapping("/api/faculty/{id}")
	public Faculty getOneEmp(@PathVariable int id) {
		return fs.getFacultyById(id);
	}
	@PostMapping("/api/faculty")
	public String add(@RequestBody Faculty faculty) {
		fs.addFaculty(faculty);
		return "success";
	}
	@DeleteMapping("/api/faculty/{id}")
	public String delete(@PathVariable int id) {
		fs.deleteFacultyById(id);
		return "success";
	}
	@PutMapping("/api/faculty")
	public String update(@RequestBody Faculty faculty) {
		fs.updateFaculty(faculty);
		return "Success";
	}
	
}
