package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.model.Teacher;
import appSoft.project.service.FacultyService;
import appSoft.project.service.TeacherService;


@Controller
public class TeacherController {
	@Autowired
	TeacherService ts;
	@Autowired
	FacultyService fs;
	
	@GetMapping("/addTeacher")
	private String teacherForm(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		return "AddTeacher";
	}
	@PostMapping("/addTeacher")
	private String addTeacher(@ModelAttribute Teacher teacher) {
		ts.addTeacher(teacher);
		return "redirect:/addTeacher";
	}
	@GetMapping("/teacherList")
	private String teacherList(Model model) {
		model.addAttribute("tList", ts.getAllTeacher());
		return "TeacherList";
	}
	@GetMapping("/deleteTeacher")
	private String deleteTeacher(@RequestParam int id) {
		ts.deleteTeacherById(id);
		return "redirect:/teacherList";
	}
	@GetMapping("/editTeacher")
	private String editTeacher(@RequestParam int id,Model model) {
		model.addAttribute("tModel",ts.getTeacherById(id));
		model.addAttribute("fList", fs.getAllFaculty());
		return "EditTeacher";
	}
	@PostMapping("/updateTeacher")
	private String updateTeacher(@ModelAttribute Teacher teacher) {
		ts.updateTeacher(teacher);
		return "redirect:/teacherList";
	}
}
