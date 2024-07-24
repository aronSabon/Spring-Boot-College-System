package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.model.Subject;
import appSoft.project.service.SubjectService;


@Controller
public class SubjectController {
	@Autowired
	SubjectService ss;
	
	@GetMapping("/addSubject")
	private String subjectForm() {
		
		return "AddSubject";
	}
	@PostMapping("/addSubject")
	private String addSubject(@ModelAttribute Subject subject) {
		ss.addSubject(subject);
		return "redirect:/addSubject";
	}
	
	@GetMapping("/subjectList")
	private String subjectList(Model model) {
		model.addAttribute("sList", ss.getAllSubject());
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
}
