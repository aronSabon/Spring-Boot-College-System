package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
}
