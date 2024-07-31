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
import org.springframework.web.multipart.MultipartFile;

import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.Salary;
import appSoft.project.model.SalarySetting;
import appSoft.project.model.Teacher;
import appSoft.project.service.FacultyService;
import appSoft.project.service.SalaryService;
import appSoft.project.service.SalarySettingService;
import appSoft.project.service.SubjectService;
import appSoft.project.service.TeacherService;


@Controller
public class TeacherController {
	@Autowired
	TeacherService ts;
	@Autowired
	FacultyService fs;
	@Autowired
	SubjectService ss;
	@Autowired
	SalarySettingService salarySettingService;
	@Autowired
	SalaryService salaryService;
	
	@GetMapping("/addTeacher")
	private String teacherForm(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		model.addAttribute("sList", ss.getAllSubject());
		return "AddTeacher";
	}
	@PostMapping("/addTeacher")
	private String addTeacher(@ModelAttribute Teacher teacher,@RequestParam MultipartFile image) {
		teacher.setImageName(image.getOriginalFilename());
		ts.addTeacher(teacher);
		

		SalarySetting salarySetting=salarySettingService.getSalarySettingByGradeAndFacultyAndSubject(teacher.getGrade(), teacher.getFaculty(), teacher.getSubject());
	

		double salaryAmount=salarySetting.getAmount();
		String[] section = teacher.getSection().split(",");
		if(section.length>1) {
			salaryAmount*=section.length;
		}
			Salary salary = new Salary();
			salary.setAmount(salaryAmount);
			salary.setFaculty(salarySetting.getFaculty());
			salary.setFullName(teacher.getFullName());
			salary.setGender(teacher.getGender());
			salary.setGrade(teacher.getGrade());
			salary.setSubject(teacher.getSubject());
			salary.setSection(teacher.getSection());
			salary.setPayDate(LocalDate.now());
			salary.setStatus(SalaryStatus.UNPAID);
			salary.setTeacherId(teacher.getTeacherId());
			salaryService.addSalary(salary);
		
		
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
		System.out.println(ts.getTeacherById(id));
		model.addAttribute("tModel",ts.getTeacherById(id));
		model.addAttribute("fList", fs.getAllFaculty());
		model.addAttribute("sList", ss.getAllSubject());

		return "EditTeacher";
	}
	@PostMapping("/updateTeacher")
	private String updateTeacher(@ModelAttribute Teacher teacher,@RequestParam MultipartFile image) {
		teacher.setImageName(image.getOriginalFilename());

		ts.updateTeacher(teacher);
		return "redirect:/teacherList";
	}
}
