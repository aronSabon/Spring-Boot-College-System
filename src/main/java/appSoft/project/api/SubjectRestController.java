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

import appSoft.project.model.FeesType;
import appSoft.project.model.Subject;
import appSoft.project.repository.SubjectRepository;
import appSoft.project.service.SubjectService;

@RestController
public class SubjectRestController {

	@Autowired   
	private SubjectService ss; 
	@Autowired
	private SubjectRepository sr;
	


	
	@GetMapping("/api/subject")
	public List<Subject> getAll() {
		return ss.getAllSubject();
	}
	@GetMapping("/api/subject/{id}")
	public Subject getOneEmp(@PathVariable int id) {
		return ss.getSubjectById(id);
	}
	@PostMapping("/api/subject")
	public String add(@RequestBody Subject subject) {
		ss.addSubject(subject);
		return "success";
	}
	@PostMapping("/api/subject/list")
	public String addList(@RequestBody Subject[] subject) {
		sr.saveAll(List.of(subject));
		
		return "success";
	}
	@DeleteMapping("/api/subject/{id}")
	public String delete(@PathVariable int id) {
		ss.deleteSubjectById(id);
		return "success";
	}
	@PutMapping("/api/subject")
	public String update(@RequestBody Subject subject) {
		ss.updateSubject(subject);
		return "Success";
	}
	
}
