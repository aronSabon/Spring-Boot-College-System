package appSoft.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import appSoft.project.model.ExpenseReport;
import appSoft.project.model.FeesPayment;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.utils.IncomeExcel;

@Controller
public class IncomeController {
	
	@Autowired
	FeesPaymentService feesPaymentService;
	
	@GetMapping("/incomeExcel")
	public  ModelAndView excel() {
		ModelAndView mv =new ModelAndView();
		mv.addObject("incomeList", feesPaymentService.getAllPayment());
		mv.setView(new IncomeExcel());
		return mv;
	}
	@GetMapping("/incomeReport")
	private String incomeReport(Model model) {
		model.addAttribute("from", LocalDate.now().minusMonths(1));
		model.addAttribute("to",LocalDate.now());
		List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(LocalDate.now().minusMonths(1), LocalDate.now());
		model.addAttribute("feesPaymentList",feesPaymentList);
//		double totalExpense= expenseList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
		double totalIncome = feesPaymentList.stream().filter(x -> x.getAmount()>0).mapToDouble(exp -> exp.getAmount() ).sum();
//		double totalIncome=0;
//		for(FeesPayment f : feesPaymentList) {
//			totalIncome+=f.getAmount();
//		}
//		model.addAttribute("totalExpense",totalExpense);
		model.addAttribute("totalIncome",totalIncome);
		return "IncomeReport";
	}
	@PostMapping("/incomeReportDetail")
	private String post(@ModelAttribute ExpenseReport expenseReport,Model model) {
		System.out.println(expenseReport);

		if(!expenseReport.getGrade().isEmpty()) {
			model.addAttribute("expenseReportModel",expenseReport);
			model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
			model.addAttribute("grade",expenseReport.getGrade());

			model.addAttribute("expenseTo",expenseReport.getExpenseTo());
			List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetweenAndGrade(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo(), expenseReport.getGrade());
			model.addAttribute("feesPaymentList",feesPaymentList);
			double totalIncome= feesPaymentList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
			
			model.addAttribute("totalIncome",totalIncome);
			return "IncomeReportDetail";

		}
		model.addAttribute("expenseReportModel",expenseReport);
		model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
		model.addAttribute("expenseTo",expenseReport.getExpenseTo());
		List<FeesPayment> feesPaymentList=feesPaymentService.getAllByDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		model.addAttribute("feesPaymentList",feesPaymentList);
		double totalIncome= feesPaymentList.stream().filter(x-> x.getAmount()> 0).mapToDouble(exp -> exp.getAmount()).sum();
		
		model.addAttribute("totalIncome",totalIncome);

		return "IncomeReportDetail";
	}
	
}
