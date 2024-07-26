package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.model.Exam;
import appSoft.project.service.FacultyService;
import appSoft.project.service.SubjectService;
import appSoft.project.service.ExamService;


@Controller
public class ExamController {
	@Autowired
	ExamService es;
	@Autowired
	FacultyService fs;
	@Autowired
	SubjectService ss;
	@GetMapping("/addExam")
	private String examForm(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		model.addAttribute("sList", ss.getAllSubject());
		return "AddExam";
	}
	@PostMapping("/addExam")
	private String addExam(@ModelAttribute Exam exam) {
		es.addExam(exam);
		return "redirect:/addExam";
	}
	@GetMapping("/examList")
	private String examList(Model model) {
		model.addAttribute("eList", es.getAllExam());
		return "ExamList";
	}
	@GetMapping("/deleteExam")
	private String deleteExam(@RequestParam int id) {
		es.deleteExamById(id);
		return "redirect:/examList";
	}
	@GetMapping("/editExam")
	private String editExam(@RequestParam int id,Model model) {
		model.addAttribute("eModel",es.getExamById(id));
		model.addAttribute("fList", fs.getAllFaculty());
		model.addAttribute("sList", ss.getAllSubject());

		return "EditExam";
	}
	@PostMapping("/updateExam")
	private String updateExam(@ModelAttribute Exam exam) {
		es.updateExam(exam);
		return "redirect:/examList";
	}
}
