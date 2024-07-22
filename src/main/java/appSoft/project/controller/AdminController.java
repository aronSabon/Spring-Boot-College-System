package appSoft.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/")
	private String getIndex() {
		return "AdminDashBoard";
	}
}
