package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.model.Faculty;
import appSoft.project.model.Faculty;
import appSoft.project.service.FacultyService;


@Controller
public class FacultyController {
	@Autowired
	FacultyService fs;
	
	@GetMapping("/addFaculty")
	private String facultyForm() {
		
		return "AddFaculty";
	}
	@PostMapping("/addFaculty")
	private String addFaculty(@ModelAttribute Faculty faculty) {
		fs.addFaculty(faculty);
		return "redirect:/addFaculty";
	}
	
	@GetMapping("/facultyList")
	private String facultyList(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		return "FacultyList";
	}
	@GetMapping("/deleteFaculty")
	private String deleteFaculty(@RequestParam int id) {
		fs.deleteFacultyById(id);
		return "redirect:/facultyList";
	}
	@GetMapping("/editFaculty")
	private String editFaculty(@RequestParam int id,Model model) {
		model.addAttribute("fModel",fs.getFacultyById(id));
		return "EditFaculty";
	}
	@PostMapping("/updateFaculty")
	private String updateFaculty(@ModelAttribute Faculty faculty) {
		fs.updateFaculty(faculty);
		return "redirect:/facultyList";
	}
}
