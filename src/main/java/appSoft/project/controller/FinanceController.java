package appSoft.project.controller;

import java.time.LocalDate;
import java.util.List;
import appSoft.project.model.ExpenseReport;
import appSoft.project.model.FeesPayment;

import appSoft.project.repository.ExpenseRepository;
import appSoft.project.service.ExpenseService;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.service.SalaryPaymentService;
import appSoft.project.utils.FinanceExcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import appSoft.project.model.Expense;

@Controller
public class FinanceController {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	ExpenseRepository expenseRepository;
	@Autowired
	FeesPaymentService feesPaymentService;
	@Autowired
	SalaryPaymentService salaryPaymentService;
	
	@GetMapping("/financeReport")
	private String expenseReport(Model model) {
		model.addAttribute("from", LocalDate.now().minusMonths(1));
		model.addAttribute("to",LocalDate.now());
		List<Expense> expenseList=expenseService.getAllByPurchaseDateBetween(LocalDate.now().minusMonths(1), LocalDate.now());
		List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(LocalDate.now().minusMonths(1), LocalDate.now());
		model.addAttribute("expenseList",expenseList);
		model.addAttribute("feesPaymentList",feesPaymentList);
		double totalExpense= expenseList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
		double totalIncome=0;
		for(FeesPayment f : feesPaymentList) {
			totalIncome+=f.getAmount();
		}
		model.addAttribute("totalExpense",totalExpense);
		model.addAttribute("totalIncome",totalIncome);
		return "FinanceReport";
	}
	@PostMapping("/financeReportDetail")
	private String post(@ModelAttribute ExpenseReport expenseReport,Model model) {

		if(!expenseReport.getGrade().isEmpty()) {
			model.addAttribute("expenseReportModel",expenseReport);
			model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
			model.addAttribute("grade",expenseReport.getGrade());

			model.addAttribute("expenseTo",expenseReport.getExpenseTo());
			List<Expense> expenseList=expenseService.getAllByPurchaseDateBetweenAndGrade(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo(), expenseReport.getGrade());
			List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetweenAndGrade(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo(), expenseReport.getGrade());
			model.addAttribute("expenseList",expenseList);
			model.addAttribute("feesPaymentList",feesPaymentList);
			double totalExpense= expenseList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
			double totalIncome=0;
			for(FeesPayment f : feesPaymentList) {
				totalIncome+=f.getAmount();
			}
			model.addAttribute("totalExpense",totalExpense);
			model.addAttribute("totalIncome",totalIncome);
			return "FinanceReportDetail";

		}
		model.addAttribute("expenseReportModel",expenseReport);
		model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
		model.addAttribute("expenseTo",expenseReport.getExpenseTo());
		List<Expense> expenseList=expenseService.getAllByPurchaseDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		model.addAttribute("expenseList",expenseList);
		model.addAttribute("feesPaymentList",feesPaymentList);
		double totalExpense= expenseList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
		double totalIncome=0;
		for(FeesPayment f : feesPaymentList) {
			totalIncome+=f.getAmount();
		}
		model.addAttribute("totalExpense",totalExpense);
		model.addAttribute("totalIncome",totalIncome);

		return "FinanceReportDetail";
	}
	

	@GetMapping("/financeExcel")
	public  ModelAndView excel(@RequestParam LocalDate expenseFrom, 
		    @RequestParam LocalDate expenseTo, 
		    @RequestParam String grade) {
		System.out.println(expenseFrom);
		System.out.println(expenseTo);
		System.out.println(grade);
		 ModelAndView mv = new ModelAndView();

		    // Fetch filtered data based on the provided filters
		    List<Expense> expenseList;
		    List<FeesPayment> feesPaymentList;
//		    List<SalaryPayment> salaryPaymentList;

		    if (!grade.isEmpty()) {
		        expenseList = expenseService.getAllByPurchaseDateBetweenAndGrade(expenseFrom, expenseTo, grade);
		        feesPaymentList = feesPaymentService.getAllByDateBetweenAndGrade(expenseFrom, expenseTo, grade);
		        System.out.println(expenseList);
		    } else {
		        expenseList = expenseService.getAllByPurchaseDateBetween(expenseFrom, expenseTo);
		        feesPaymentList = feesPaymentService.getAllByDateBetween(expenseFrom, expenseTo);
		    }

//		    salaryPaymentList = salaryPaymentService.getAllByDateBetween(expenseFrom, expenseTo);

		    // Add filtered lists to the model
		    mv.addObject("expenseList", expenseList);
//		    mv.addObject("salaryPaymentList", salaryPaymentList);
		    mv.addObject("feesPaymentList", feesPaymentList);

		    mv.setView(new FinanceExcel());
		    return mv;
		
		
		
//		ModelAndView mv =new ModelAndView();
//		mv.addObject("expenseList", expenseService.getAllExpense());
//		mv.addObject("salaryPaymentList", salaryPaymentService.getAllPayment());
//		System.out.println(salaryPaymentService.getAllPayment());
//		System.out.println(salaryPaymentService.getAllPayment());
//		System.out.println(salaryPaymentService.getAllPayment());
//
//		mv.addObject("feesPaymentList", feesPaymentService.getAllPayment());
//
//
//		mv.setView(new FinanceExcel());
//		return mv;
	}
}
