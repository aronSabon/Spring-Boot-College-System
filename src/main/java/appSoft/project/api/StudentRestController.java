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
//import org.springframework.web.client.RestTemplate;

import appSoft.project.model.Student;
import appSoft.project.repository.StudentRepository;
import appSoft.project.service.StudentService;

@RestController
public class StudentRestController {

	@Autowired   
	private StudentService ss;  

	@Autowired   
	private StudentRepository sr; 


	
	@GetMapping("/api/student")
	public List<Student> getAll() {
		return ss.getAllStudent();
	}
	@GetMapping("/api/student/{id}")
	public Student getOneEmp(@PathVariable int id) {
		return ss.getStudentById(id);
	}
	@PostMapping("/api/student")
	public String add(@RequestBody Student student) {
		ss.addStudent(student);
		return "success";
	}
	@DeleteMapping("/api/student/{id}")
	public String delete(@PathVariable int id) {
		ss.deleteStudentById(id);
		return "success";
	}
	@PutMapping("/api/student")
	public String update(@RequestBody Student student) {
		ss.updateStudent(student);
		return "Success";
	}
	
	
//	@GetMapping("/api/student/j2o")
//	public String jsonToObject() {
//		RestTemplate rest= new RestTemplate();
//		Student student =rest.getForObject("http://localhost:8080/api/student/6", Student.class);
//		
//		return student.getFullName();
//	}
//	@GetMapping("/api/student/ja2oa")
//	public String jsonArrayToObjArray() {
//		RestTemplate rest = new RestTemplate();
//		Student[] student = rest.getForObject("http://localhost:8080/api/student", Student[].class);
//		return null;
//	}
//	@GetMapping("/api/studentLoad")
//	public String loadProducts() {
//		RestTemplate rest= new RestTemplate();
//		Student[] student =rest.getForObject("http://localhost:8080/api/student", Student[].class);
//		sr.saveAll(List.of(student));
//		return "success";
//	}
	
}
