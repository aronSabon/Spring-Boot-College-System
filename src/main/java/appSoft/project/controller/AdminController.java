package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import appSoft.project.model.Admin;
import appSoft.project.model.SalaryPayment;
import appSoft.project.service.AdminService;
import appSoft.project.service.ExpenseService;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.service.SalaryPaymentService;
import appSoft.project.utils.FeesExcel;
import appSoft.project.utils.FinanceExcel;

@Controller
public class AdminController {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	SalaryPaymentService salaryPaymentService;
	@Autowired
	FeesPaymentService feesPaymentService;
	@Autowired
	AdminService adminService;
	
	@GetMapping("/")
	private String getIndex() {
		return "Login";
	}
	@GetMapping("/ajaxTest")
	private String getIndexsd() {
		return "AjaxTest";
	}
	@PostMapping("/login")
	private String postLogin(@ModelAttribute Admin admin, Model model) {
		Admin adminCheck=adminService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if(adminCheck==null) {
			model.addAttribute("LoginMessage","user doesnot exist");
			return"Login";
			
		}
		else return"AdminDashboard";
	}
	
	@GetMapping("/register")
	private String register() {
		return"Register";
	}
	
	@PostMapping("/register")
	private String postRegister(@ModelAttribute Admin admin,Model model) {
		Admin adminCheck = adminService.findByUsername(admin.getUsername());
		if(adminCheck!=null) {
			model.addAttribute("message","username already exists");
			return"Register";
		}
		adminService.addAdmin(admin);
		return "redirect:/";
	}
	
	
	@GetMapping("/financeExcel")
	public  ModelAndView excel() {
		ModelAndView mv =new ModelAndView();
		mv.addObject("expenseList", expenseService.getAllExpense());
		mv.addObject("salaryPaymentList", salaryPaymentService.getAllPayment());
		System.out.println(salaryPaymentService.getAllPayment());
		System.out.println(salaryPaymentService.getAllPayment());
		System.out.println(salaryPaymentService.getAllPayment());

		mv.addObject("feesPaymentList", feesPaymentService.getAllPayment());


		mv.setView(new FinanceExcel());
		return mv;
	}
}
