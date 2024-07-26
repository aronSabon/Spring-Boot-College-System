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


import appSoft.project.model.Exam;
import appSoft.project.service.ExamService;

@RestController
public class ExamRestController {

	@Autowired   
	private ExamService es;  
	


	
	@GetMapping("/api/exam")
	public List<Exam> getAll() {
		return es.getAllExam();
	}
	@GetMapping("/api/exam/{id}")
	public Exam getOneEmp(@PathVariable int id) {
		return es.getExamById(id);
	}
	@PostMapping("/api/exam")
	public String add(@RequestBody Exam exam) {
		es.addExam(exam);
		return "success";
	}
	@DeleteMapping("/api/exam/{id}")
	public String delete(@PathVariable int id) {
		es.deleteExamById(id);
		return "success";
	}
	@PutMapping("/api/exam")
	public String update(@RequestBody Exam exam) {
		es.updateExam(exam);
		return "Success";
	}
	
}
