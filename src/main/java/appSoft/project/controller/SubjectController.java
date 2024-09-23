package appSoft.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import appSoft.project.model.Faculty;
import appSoft.project.model.Subject;
import appSoft.project.service.FacultyService;
import appSoft.project.service.SubjectService;


@Controller
public class SubjectController {
	@Autowired
	SubjectService ss;
	@Autowired
	FacultyService facultyService;
	
	@GetMapping("/addSubject")
	private String subjectForm(Model model) {
		model.addAttribute("facultyList",facultyService.getAllFaculty());
		
		
		return "AddSubject";
	}
	@PostMapping("/addSubject")
	private String addSubject(@ModelAttribute Subject subject) {
		ss.addSubject(subject);
		return "redirect:/addSubject";
	}
	
	@GetMapping("/subjectList")
	private String subjectList(Model model) {
		model.addAttribute("subjectList", ss.getAllSubject());
		return "SubjectList";
	}
	
	
	
	@GetMapping("/deleteSubject")
	private String deleteSubject(@RequestParam int id) {
		ss.deleteSubjectById(id);
		return "redirect:/subjectList";
	}
	@GetMapping("/editSubject")
	private String editSubject(@RequestParam int id,Model model) {
		model.addAttribute("sModel",ss.getSubjectById(id));
		return "EditSubject";
	}
	@PostMapping("/updateSubject")
	private String updateSubject(@ModelAttribute Subject subject) {
		ss.updateSubject(subject);
		return "redirect:/subjectList";
	}
	
	@GetMapping("/subject/{grade}")
	@ResponseBody
	public List<Subject> getSubjectByGrade(@PathVariable String grade,Model model) {
		System.out.println( ss.getAllSubjecByGrade(grade).size());
		return ss.getAllSubjecByGrade(grade);
	}
	@GetMapping("/subject/{faculty}/{grade}")
	@ResponseBody
	public List<Subject> getSubjectByFacultyAndGrade(@PathVariable String grade,Model model, @PathVariable Faculty faculty) {
		System.out.println( ss.getAllSubjecByGrade(grade).size());
		return ss.getAllSubjectByFacultyAndGrade(faculty,grade);
	}
}
