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

import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Fees;
import appSoft.project.service.FeesService;
import appSoft.project.service.StudentService;


@Controller
public class FeesController {
	@Autowired
	FeesService fs;
	@Autowired
	StudentService ss;
	
	@GetMapping("/addFees")
	private String feesForm(Model model) {
		model.addAttribute("date", LocalDate.now());
		
		return "AddFees";
	}
	@GetMapping("/addFeesFromList")
	private String feesFormm( @RequestParam int id,Model model) {
		model.addAttribute("fModel",fs.getFeesById(id));
		model.addAttribute("date", LocalDate.now());
		return "AddFeesFromList";
	}
	@PostMapping("/addFeesFromList")
	private String addFeesFromList(@ModelAttribute Fees fees) {
		fees.setStatus(FeesStatus.UNPAID);
		fs.addFees(fees);
		
		
		return "redirect:/feesList";
	}
	@PostMapping("/addFees")
	private String addFees(@ModelAttribute Fees fees) {
		fees.setStatus(FeesStatus.UNPAID);
		fs.addFees(fees);
		
		
		return "redirect:/addFees";
	}
	
	@GetMapping("/feesList")
	private String feesList(Model model) {
		model.addAttribute("fList", fs.getAllFees());
		return "FeesList";
	}
	@GetMapping("/deleteFees")
	private String deleteFees(@RequestParam int id) {
		fs.deleteFeesById(id);
		return "redirect:/feesList";
	}
	@GetMapping("/editFees")
	private String editFees(@RequestParam int id,Model model) {
		model.addAttribute("fModel",fs.getFeesById(id));
		return "EditFees";
	}
	@PostMapping("/updateFees")
	private String updateFees(@ModelAttribute Fees fees) {
		fs.updateFees(fees);
		return "redirect:/feesList";
	}
	@GetMapping("/paymentDetails")
	private String paymentDetails(@RequestParam int id,Model model) {
		model.addAttribute("fList",fs.getAllFeesByStudentId(id));
		model.addAttribute("sModel",ss.getStudentByRollNo(id));
		model.addAttribute("upfList", fs.getAllFeesByStudentIdAndStatus(id, FeesStatus.UNPAID));
		
		List<Fees>fees=fs.getAllFeesByStudentIdAndStatus(id, FeesStatus.UNPAID);
		double subTotal=0;
		int dueViolation=0;
		for(Fees f : fees) {
			subTotal+=f.getAmount();
		}
		for(Fees f : fees) {
			if (f.getDueDate().isAfter(LocalDate.now())) {
				dueViolation+=1;
			}
		}
		double dueViolationFee = dueViolation*1000;
		double total=subTotal+dueViolationFee;
		model.addAttribute("subTotal",subTotal);
		model.addAttribute("dueViolation",dueViolation);
		model.addAttribute("dueViolationFee",dueViolationFee);
		model.addAttribute("total",total);
		
		
		
		return "PaymentDetails";
	}
}
