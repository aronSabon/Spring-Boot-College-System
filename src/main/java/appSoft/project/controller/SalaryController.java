package appSoft.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Salary;
import appSoft.project.service.FacultyService;
import appSoft.project.service.SalaryService;
import appSoft.project.service.SubjectService;
import appSoft.project.service.TeacherService;


@Controller
public class SalaryController {
	@Autowired
	SalaryService ss;
	@Autowired
	FacultyService facultyService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	SubjectService subjectService;
	
	
	@GetMapping("/addSalary")
	private String salaryForm(Model model) {
		model.addAttribute("facultyList",facultyService.getAllFaculty());
		model.addAttribute("subjectList",subjectService.getAllSubject());
		return "AddSalary";
	}
	
	@PostMapping("/addSalary")
	private String addSalary(@ModelAttribute Salary salary) {
		salary.setStatus(SalaryStatus.UNPAID);
		ss.addSalary(salary);
		return "redirect:/addSalary";
	}
	
	@GetMapping("/salaryList")
	private String salaryList(Model model) {
		model.addAttribute("sList", ss.getAllSalary());
		return "SalaryList";
	}
	@GetMapping("/deleteSalary")
	private String deleteSalary(@RequestParam int id) {
		ss.deleteSalaryById(id);
		return "redirect:/salaryList";
	}
	@GetMapping("/editSalary")
	private String editSalary(@RequestParam int id,Model model) {
		model.addAttribute("salaryModel",ss.getSalaryById(id));
		model.addAttribute("facultyList",facultyService.getAllFaculty());

		return "EditSalary";
	}
	@PostMapping("/updateSalary")
	private String updateSalary(@ModelAttribute Salary salary) {
		ss.updateSalary(salary);
		return "redirect:/salaryList";
	}
	
	

}
