package appSoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import appSoft.project.model.SalaryPayment;
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
	
	@GetMapping("/")
	private String getIndex() {
		return "AdminDashBoard";
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
