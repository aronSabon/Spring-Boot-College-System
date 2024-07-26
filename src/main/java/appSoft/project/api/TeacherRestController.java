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


import appSoft.project.model.Teacher;
import appSoft.project.service.TeacherService;

@RestController
public class TeacherRestController {

	@Autowired   
	private TeacherService ts;  
	


	
	@GetMapping("/api/teacher")
	public List<Teacher> getAll() {
		return ts.getAllTeacher();
	}
	@GetMapping("/api/teacher/{id}")
	public Teacher getOneEmp(@PathVariable int id) {
		return ts.getTeacherById(id);
	}
	@PostMapping("/api/teacher")
	public String add(@RequestBody Teacher teacher) {
		ts.addTeacher(teacher);
		return "success";
	}
	@DeleteMapping("/api/teacher/{id}")
	public String delete(@PathVariable int id) {
		ts.deleteTeacherById(id);
		return "success";
	}
	@PutMapping("/api/teacher")
	public String update(@RequestBody Teacher teacher) {
		ts.updateTeacher(teacher);
		return "Success";
	}
	
}
