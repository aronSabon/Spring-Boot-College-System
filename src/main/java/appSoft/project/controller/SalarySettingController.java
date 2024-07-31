package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.SalarySetting;
import appSoft.project.service.FacultyService;
import appSoft.project.service.SubjectService;
import appSoft.project.service.SalarySettingService;


@Controller
public class SalarySettingController {
	@Autowired
	SalarySettingService salarySettingService;
	@Autowired
	FacultyService fs;
	@Autowired
	SubjectService ss;
	
	@GetMapping("/addSalarySetting")
	private String salarySettingForm(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		model.addAttribute("sList", ss.getAllSubject());
		return "AddSalarySetting";
	}
	@PostMapping("/addSalarySetting")
	private String addSalarySetting(@ModelAttribute SalarySetting salarySetting) {
		salarySettingService.addSalarySetting(salarySetting);
		return "redirect:/addSalarySetting";
	}
	@GetMapping("/salarySettingList")
	private String salarySettingList(Model model) {
		model.addAttribute("salarySettingList", salarySettingService.getAllSalarySetting());
		return "SalarySettingList";
	}
	@GetMapping("/deleteSalarySetting")
	private String deleteSalarySetting(@RequestParam int id) {
		salarySettingService.deleteSalarySettingById(id);
		return "redirect:/salarySettingList";
	}
	@GetMapping("/editSalarySetting")
	private String editSalarySetting(@RequestParam int id,Model model) {
		model.addAttribute("salarySettingModel",salarySettingService.getSalarySettingById(id));
		model.addAttribute("fList", fs.getAllFaculty());
		model.addAttribute("sList", ss.getAllSubject());

		return "EditSalarySetting";
	}
	@PostMapping("/updateSalarySetting")
	private String updateSalarySetting(@ModelAttribute SalarySetting salarySetting) {

		salarySettingService.updateSalarySetting(salarySetting);
		return "redirect:/salarySettingList";
	}
}
