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
import org.springframework.web.servlet.ModelAndView;

import appSoft.project.model.Expense;
import appSoft.project.model.ExpenseReport;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesPayment;
import appSoft.project.model.Salary;
import appSoft.project.model.SalaryPayment;
import appSoft.project.repository.ExpenseRepository;
import appSoft.project.service.ExpenseService;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.service.SalaryPaymentService;
import appSoft.project.service.SalaryService;
import appSoft.project.utils.ExpenseExcel;
import appSoft.project.utils.TeacherExcel;


@Controller
public class ExpenseController {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	ExpenseRepository expenseRepository;
	@Autowired
	FeesPaymentService feesPaymentService;
	@Autowired
	SalaryPaymentService salaryPaymentService;

	@GetMapping("/addExpense")
	private String expenseForm(Model model) {
		model.addAttribute("date",LocalDate.now());
		return "AddExpense";
	}
	@PostMapping("/addExpense")
	private String addExpense(@ModelAttribute Expense expense) {
		expenseService.addExpense(expense);
		return "redirect:/addExpense";
	}

	@GetMapping("/expenseList")
	private String expenseList(Model model) {
		model.addAttribute("expenseList", expenseService.getAllExpense());
		return "ExpenseList";
	}
	@GetMapping("/deleteExpense")
	private String deleteExpense(@RequestParam int id) {
		expenseService.deleteExpenseById(id);
		return "redirect:/expenseList";
	}
	@GetMapping("/editExpense")
	private String editExpense(@RequestParam int id,Model model) {
		model.addAttribute("expenseModel",expenseService.getExpenseById(id));
		return "EditExpense";
	}
	@PostMapping("/updateExpense")
	private String updateExpense(@ModelAttribute Expense expense) {
		expenseService.updateExpense(expense);
		return "redirect:/expenseList";
	}
	@GetMapping("/expenseExcel")
	public  ModelAndView excel() {
		ModelAndView mv =new ModelAndView();
		mv.addObject("expenseList", expenseService.getAllExpense());
		mv.setView(new ExpenseExcel());
		return mv;
	}
	
	@GetMapping("/expenseReport")
	private String expenseReport(Model model) {
		model.addAttribute("from", LocalDate.now().minusMonths(1));
		model.addAttribute("to",LocalDate.now());

		List<Expense> expenseList=expenseService.getAllByPurchaseDateBetween(LocalDate.now().minusMonths(1), LocalDate.now());
		List<SalaryPayment> salaryPaymentList= salaryPaymentService.getAllByDateBetween(LocalDate.now().minusMonths(1), LocalDate.now());
		List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(LocalDate.now().minusMonths(1), LocalDate.now());
		model.addAttribute("salaryPaymentList",salaryPaymentList);
		model.addAttribute("expenseList",expenseList);
		model.addAttribute("feesPaymentList",feesPaymentList);
		
//		for(Expense e : expenseList) {
//			totalExpense+=e.getAmount();
//		}
		double totalExpense= expenseList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
		double ts = salaryPaymentList.stream().filter( x -> x.getAmount() > 0).mapToDouble(sal -> sal.getAmount()).sum();
		totalExpense +=ts; 
		
		double totalIncome=0;
		for(FeesPayment f : feesPaymentList) {
			totalIncome+=f.getAmount();
		}
		model.addAttribute("totalExpense",totalExpense);
		model.addAttribute("totalIncome",totalIncome);
		return "ExpenseReport";
	}
	@PostMapping("/expenseReportDetail")
	private String post(@ModelAttribute ExpenseReport expenseReport,Model model) {

		if(!expenseReport.getGrade().isEmpty()) {
			model.addAttribute("expenseReportModel",expenseReport);
			model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
			model.addAttribute("expenseTo",expenseReport.getExpenseTo());
			List<Expense> expenseList=expenseService.getAllByPurchaseDateBetweenAndGrade(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo(), expenseReport.getGrade());
			List<SalaryPayment> salaryPaymentList= salaryPaymentService.getAllByDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
			List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
			model.addAttribute("salaryPaymentList",salaryPaymentList);
			model.addAttribute("expenseList",expenseList);
			model.addAttribute("feesPaymentList",feesPaymentList);
			double totalExpense=0;
			for(Expense e : expenseList) {
				totalExpense+=e.getAmount();
			}
			double totalIncome=0;
			for(FeesPayment f : feesPaymentList) {
				totalIncome+=f.getAmount();
			}
			model.addAttribute("totalExpense",totalExpense);
			model.addAttribute("totalIncome",totalIncome);
			return "ExpenseReportDetail";

		}
		model.addAttribute("expenseReportModel",expenseReport);
		model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
		model.addAttribute("expenseTo",expenseReport.getExpenseTo());
		List<Expense> expenseList=expenseService.getAllByPurchaseDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		List<SalaryPayment> salaryPaymentList= salaryPaymentService.getAllByDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		model.addAttribute("expenseList",expenseList);
		model.addAttribute("salaryPaymentList",salaryPaymentList);
		model.addAttribute("feesPaymentList",feesPaymentList);
		double totalExpense=0;
		for(Expense e : expenseList) {
			totalExpense+=e.getAmount();
		}
		double totalIncome=0;
		for(FeesPayment f : feesPaymentList) {
			totalIncome+=f.getAmount();
		}
		model.addAttribute("totalExpense",totalExpense);
		model.addAttribute("totalIncome",totalIncome);

		return "ExpenseReportDetail";
	}

}