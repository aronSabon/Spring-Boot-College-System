package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.model.Transport;
import appSoft.project.service.TransportService;


@Controller
public class TransportController {
	@Autowired
	TransportService ts;
	
	@GetMapping("/addTransport")
	private String transportForm() {
		
		return "AddTransport";
	}
	@PostMapping("/addTransport")
	private String addTransport(@ModelAttribute Transport transport) {
		ts.addTransport(transport);
		return "redirect:/addTransport";
	}
	
	@GetMapping("/transportList")
	private String transportList(Model model) {
		model.addAttribute("tList", ts.getAllTransport());
		return "TransportList";
	}
	@GetMapping("/deleteTransport")
	private String deleteTransport(@RequestParam int id) {
		ts.deleteTransportById(id);
		return "redirect:/transportList";
	}
	@GetMapping("/editTransport")
	private String editTransport(@RequestParam int id,Model model) {
		model.addAttribute("tModel",ts.getTransportById(id));
		return "EditTransport";
	}
	@PostMapping("/updateTransport")
	private String updateTransport(@ModelAttribute Transport transport) {
		ts.updateTransport(transport);
		return "redirect:/transportList";
	}
}
