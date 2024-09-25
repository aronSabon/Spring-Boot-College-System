package appSoft.project.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import appSoft.project.constant.FeesStatus;
import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Expense;
import appSoft.project.model.Fees;
import appSoft.project.model.Salary;
import appSoft.project.model.SalaryPayment;

import appSoft.project.model.Student;
import appSoft.project.model.Teacher;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.SalaryPaymentService;
import appSoft.project.service.SalaryService;
import appSoft.project.service.ExpenseService;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.service.StudentService;
import appSoft.project.service.TeacherService;

@Controller
public class SalaryPaymentController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	SalaryService salaryService;
	@Autowired
	SalaryPaymentService salaryPaymentService;
	@Autowired
	ExpenseService expenseService;

	@GetMapping("/salaryPaymentDetails")
	private String salaryDetails(@RequestParam int id,Model model) {
		//for disabling salary pay
		List<Salary> salaryList = salaryService.getAllSalaryByTeacherId(id);
		boolean allPaid = salaryList.stream().allMatch(s -> s.getStatus().equals(SalaryStatus.PAID));
		System.out.println(allPaid);
		model.addAttribute("allPaid", allPaid);
		//till here
		model.addAttribute("teacherModel",teacherService.getTeacherById(id));
		model.addAttribute("salaryList",salaryService.getAllSalaryByTeacherId(id));
		model.addAttribute("paymentHistory",salaryPaymentService.getAllByTeacherId(id));
		return "SalaryPaymentDetails";
	}

	@GetMapping("/teacherPayment")
	private String teacherPayment( @RequestParam int id ,Model model) {
		Teacher teacher= teacherService.getTeacherById(id);
		model.addAttribute("teacher",teacher);
		model.addAttribute("date",LocalDate.now());
		model.addAttribute("time",LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		List<Salary>salaryDue=salaryService.getAllByTeacherIdAndStatus(id, SalaryStatus.DUE);
		List<Salary>salaryUnpaid=salaryService.getAllByTeacherIdAndStatus(id, SalaryStatus.UNPAID);
		List<Salary>salaryFilter = new ArrayList<>();
		salaryFilter.addAll(salaryDue);
		salaryFilter.addAll(salaryUnpaid);


		model.addAttribute("salaryList",salaryFilter);
		return"TeacherPayment";
	}


	@PostMapping("/teacherPayment")
	private String SalaryPayment(@ModelAttribute SalaryPayment salaryPayment, RedirectAttributes redirectAttribute) {
		System.out.println(salaryPayment);
		String[] month =(salaryPayment.getMonth().split(","));

		List<Salary>salaryDue=salaryService.getAllByTeacherIdAndStatus(salaryPayment.getTeacherId(), SalaryStatus.DUE);
		List<Salary>salaryUnpaid=salaryService.getAllByTeacherIdAndStatus(salaryPayment.getTeacherId(), SalaryStatus.UNPAID);
		List<Salary>salaryFilter = new ArrayList<>();
		salaryFilter.addAll(salaryDue);
		salaryFilter.addAll(salaryUnpaid);
		double totalPayment=salaryPayment.getAmount();
		double totalPayable = 0;

		for(Salary i : salaryFilter) {
			for(int j=0; j<month.length; j++) {
				totalPayable += i.getAmount()-i.getAmountPaid();
			}
		}
	

		if(totalPayment>totalPayable) {
			redirectAttribute.addFlashAttribute("message", "Total Payment exceeding total payable amount!!!");
			redirectAttribute.addFlashAttribute("status", "error");
			redirectAttribute.addAttribute("id",salaryPayment.getTeacherId());

			return "redirect:/teacherPayment";
		}
		else {
			for(Salary i : salaryFilter) {
				for(int j=0; j<month.length; j++) {
					if(i.getMonth().equals(month[j])) {
						if(totalPayment>0) {
							if(totalPayment>(i.getAmount()-i.getAmountPaid())) {
								SalaryPayment salaryp= new SalaryPayment();
								salaryp.setAmount(i.getAmount()-i.getAmountPaid());
								salaryp.setDate(salaryPayment.getDate());
								salaryp.setFullName(salaryPayment.getFullName());
								salaryp.setMonth(month[j]);
								salaryp.setPaidWith(salaryPayment.getPaidWith());
								salaryp.setTeacherId(salaryPayment.getTeacherId());
								salaryp.setTime(salaryPayment.getTime());
								salaryPaymentService.addPayment(salaryp);
								totalPayment=totalPayment-(i.getAmount()-i.getAmountPaid());
								i.setStatus(SalaryStatus.PAID);
								i.setAmountPaid(i.getAmountPaid()+(i.getAmount()-i.getAmountPaid()));
								salaryService.updateSalary(i);

								//add into expense
								Expense expense = new Expense();
								expense.setExpenseType("Salary");
								expense.setAmount(salaryp.getAmount());
								expense.setGrade(salaryFilter.get(0).getGrade());
								expense.setParticulars(salaryFilter.get(0).getFullName());
								expense.setPurchaseDate(salaryp.getDate());
								expense.setRemarks("salary paid");
								expense.setPurchaseBy("Admin");
								expenseService.addExpense(expense);
							}
							else if(totalPayment==(i.getAmount()-i.getAmountPaid())) {
								SalaryPayment salaryp= new SalaryPayment();
								salaryp.setAmount(i.getAmount()-i.getAmountPaid());
								salaryp.setDate(salaryPayment.getDate());
								salaryp.setFullName(salaryPayment.getFullName());
								salaryp.setMonth(month[j]);
								salaryp.setPaidWith(salaryPayment.getPaidWith());
								salaryp.setTeacherId(salaryPayment.getTeacherId());
								salaryp.setTime(salaryPayment.getTime());
								salaryPaymentService.addPayment(salaryp);
								totalPayment=totalPayment-(i.getAmount()-i.getAmountPaid());
								i.setStatus(SalaryStatus.PAID);
								i.setAmountPaid(i.getAmountPaid()+(i.getAmount()-i.getAmountPaid()));
								salaryService.updateSalary(i);

								//add into expense
								Expense expense = new Expense();
								expense.setExpenseType("Salary");
								expense.setAmount(salaryp.getAmount());
								expense.setGrade(salaryFilter.get(0).getGrade());
								expense.setParticulars(salaryFilter.get(0).getFullName());
								expense.setPurchaseDate(salaryp.getDate());
								expense.setRemarks("salary paid");
								expense.setPurchaseBy("Admin");

								expenseService.addExpense(expense);

							}
							else if(totalPayment<(i.getAmount()-i.getAmountPaid())) {  
								SalaryPayment salaryp= new SalaryPayment();
								salaryp.setAmount(totalPayment);
								salaryp.setDate(salaryPayment.getDate());
								salaryp.setFullName(salaryPayment.getFullName());
								salaryp.setMonth(month[j]);
								salaryp.setPaidWith(salaryPayment.getPaidWith());
								salaryp.setTeacherId(salaryPayment.getTeacherId());
								salaryp.setTime(salaryPayment.getTime());
								salaryPaymentService.addPayment(salaryp);
								i.setAmountPaid(i.getAmountPaid()+totalPayment);
								salaryService.updateSalary(i);
								totalPayment=totalPayment-totalPayment;

								//add into expense
								Expense expense = new Expense();
								expense.setExpenseType("Salary");
								expense.setAmount(salaryp.getAmount());
								expense.setGrade(salaryFilter.get(0).getGrade());
								expense.setParticulars(salaryFilter.get(0).getFullName());
								expense.setPurchaseDate(salaryp.getDate());
								expense.setRemarks("salary paid");
								expense.setPurchaseBy("Admin");

								expenseService.addExpense(expense);

							}
						}
					}
				}
			}
			redirectAttribute.addFlashAttribute("message", "Payment Success!!!");
			redirectAttribute.addFlashAttribute("status", "success");

		}
		redirectAttribute.addAttribute("id",salaryPayment.getTeacherId());
		return "redirect:/teacherPayment";
	}
	//
	//	@GetMapping("/paymentDetails")
	//	private String paymentDetails(@RequestParam int rollNo,Model model) {
	//		model.addAttribute("feesList",feesService.getAllFeesByRollNo(rollNo));
	//		
	//		
	//		List<Fees>feesDue=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.DUE);
	//		List<Fees>feesUnpaid=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
	//		List<Fees>feesFilter = new ArrayList<>();
	//		feesFilter.addAll(feesDue);
	//		feesFilter.addAll(feesUnpaid);
	//		
	//		model.addAttribute("duesFilteredList", feesFilter);
	//System.out.println(feesFilter);
	//System.out.println(feesFilter);
	//System.out.println(feesFilter);
	//System.out.println(feesFilter);
	//
	//		double subTotal=0;
	//		double discount=0;
	//		for(Fees f : feesFilter) {
	//			subTotal+=f.getAmount();
	//		}
	//		double total=subTotal-discount;
	//		
	//		Student student = studentService.getStudentByRollNo(rollNo);
	//
	//		model.addAttribute("sModel",student);
	//		model.addAttribute("subTotal",subTotal);
	//		model.addAttribute("total",total);
	//		model.addAttribute("rollNo",student.getRollNo());
	//		model.addAttribute("paymentList",feesPaymentService.getAllByRollNo(rollNo));
	//
	//		
	//		return "FeesPaymentDetails";
	//	}
	//
	//	@GetMapping("/studentPayment")
	//	private String studentPayment(@RequestParam int rollNo, Model model) {
	//		Student student= studentService.getStudentByRollNo(rollNo);
	//		model.addAttribute("student",studentService.getStudentByRollNo(rollNo));
	//		model.addAttribute("date",LocalDate.now());
	//		model.addAttribute("time",LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
	//		model.addAttribute("feeTypeList",feesTypeService.getFeesTypeByGradeAndFaculty(student.getGrade(), student.getFaculty()));
	//		List<Fees>feesDue=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.DUE);
	//		List<Fees>feesUnpaid=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
	//		List<Fees>feesFilter = new ArrayList<>();
	//		feesFilter.addAll(feesDue);
	//		feesFilter.addAll(feesUnpaid);
	//		
	//		
	//		model.addAttribute("fList",feesFilter);
	//
	//		return "StudentPayment";
	//	}
	//
	//	@PostMapping("/payment")
	//	private String feesPayment(@ModelAttribute FeesPayment feesPayment, RedirectAttributes redirectAttribute) {
	//
	//
	////		String[] split =(payment.getFeesType().split(","));
	////		for(int i =0; i<split.length ; i++) {
	////		System.out.println(split[i]);
	////		
	////	}
	//		List<Fees>feesDue=feesService.getAllFeesByRollNoAndStatus(feesPayment.getRollNo(), FeesStatus.DUE);
	//		List<Fees>feesUnpaid=feesService.getAllFeesByRollNoAndStatus(feesPayment.getRollNo(), FeesStatus.UNPAID);
	//		List<Fees>feesFilter = new ArrayList<>();
	//		feesFilter.addAll(feesDue);
	//		feesFilter.addAll(feesUnpaid);
	//
	//		// for multiple feesType input
	//		double totalPayment=feesPayment.getAmount();
	//		String[] feesType =(feesPayment.getFeesType().split(","));
	//		
	//		for(Fees i : feesFilter) {
	//			for(int a = 0; a<feesType.length ; a++) {
	//				if(i.getFeesType().equals(feesType[a])) {
	//					if(totalPayment>0) {
	//						if(totalPayment>i.getAmount()) {
	//							totalPayment=totalPayment-i.getAmount();
	//							i.setStatus(FeesStatus.PAID);
	//							i.setAmountPaid(i.getAmountPaid()+i.getAmount());
	//							feesService.updateFees(i);
	//						    feesPaymentService.addPayment(feesPayment);
	//						}
	//						else if(totalPayment==i.getAmount()) {
	//							totalPayment=totalPayment-i.getAmount();
	//							i.setStatus(FeesStatus.PAID);
	//							i.setAmountPaid(i.getAmountPaid()+i.getAmount());
	//
	//							feesService.updateFees(i);
	//							feesPaymentService.addPayment(feesPayment);
	//						}
	//						else if(totalPayment<i.getAmount()) {  
	//							i.setAmountPaid(i.getAmountPaid()+totalPayment);
	//							feesService.updateFees(i);
	//							feesPaymentService.addPayment(feesPayment);
	//						}
	//					}
	//				}
	//			}
	//		}
	//		redirectAttribute.addAttribute("rollNo",feesPayment.getRollNo());
	//		return	 "redirect:/studentPayment";
	//
	//
	//		//		Fees fees=feesService.getFeesByRollNoAndStatusAndFeesType(payment.getRollNo(), FeesStatus.DUE, payment.getFeesType());
	//
	//
	//
	//		//working for single feesType input
	//		//		for(Fees i : feesFilter) {
	//		//			if(payment.getFeesType().equals(i.getFeesType())) {
	//		//				if(payment.getAmount()==i.getAmount()) {
	//		//					i.setStatus(FeesStatus.PAID);
	//		//					feesService.updateFees(i);
	//		//					ps.addPayment(payment);
	//		//				}
	//		//				else if(payment.getAmount()<i.getAmount()) {
	//		//					i.setAmount(i.getAmount()-payment.getAmount());
	//		//					feesService.updateFees(i);
	//		//					ps.addPayment(payment);
	//		//
	//		//				}
	//		//			}
	//		//		}
	//		//		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
	//		//		return	 "redirect:/studentPayment";
	//
	//
	//
	//		//working for single feesType INput
	//		//		Fees fees=feesService.getFeesByRollNoAndStatusAndFeesType(payment.getRollNo(), FeesStatus.DUE, payment.getFeesType());
	//		//		if(payment.getAmount()==fees.getAmount()) {
	//		//			fees.setStatus(FeesStatus.PAID);
	//		//			feesService.updateFees(fees);
	//		//		}
	//		//		else if(payment.getAmount()<fees.getAmount()) {
	//		//			fees.setAmount(fees.getAmount()-payment.getAmount());
	//		//			feesService.updateFees(fees);
	//		//		}
	//		//		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
	//		//		return	 "redirect:/studentPayment";
	//	}
}
