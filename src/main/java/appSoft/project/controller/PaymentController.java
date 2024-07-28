package appSoft.project.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import appSoft.project.model.Fees;
import appSoft.project.model.Payment;
import appSoft.project.model.Student;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.PaymentService;
import appSoft.project.service.StudentService;

@Controller
public class PaymentController {
	@Autowired
	FeesService fs;
	@Autowired
	StudentService ss;

	@Autowired
	private FeesTypeService  feesTypeService;
	@Autowired
	private PaymentService ps;



	@GetMapping("/paymentDetails")
	private String paymentDetails(@RequestParam int rollNo,Model model) {


		model.addAttribute("sModel",ss.getStudentByRollNo(rollNo));
		Student student = ss.getStudentByRollNo(rollNo);


		List<Fees>feesDue=fs.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.DUE);
		List<Fees>feesUnpaid=fs.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
		List<Fees>feesFilter = new ArrayList<>();
		feesFilter.addAll(feesDue);
		feesFilter.addAll(feesUnpaid);
		model.addAttribute("dfList", feesFilter);



		double subTotal=0;
		double discount=0;
		for(Fees f : feesFilter) {
			subTotal+=f.getAmount();
		}

		double total=subTotal-discount;
		model.addAttribute("subTotal",subTotal);

		model.addAttribute("total",total);



		//		List<Fees>feesList=fs.getAllFeesByRollNo(rollNo);
		//		for(Fees i : feesList) {
		//			if(i.getDueDate().isBefore(LocalDate.now())) {
		//				i.setStatus(FeesStatus.DUE);
		//			}
		//		}

		model.addAttribute("fList",fs.getAllFeesByRollNo(rollNo));
		model.addAttribute("rollNo",student.getRollNo());
		model.addAttribute("paymentList",ps.getAllByRollNo(rollNo));

		return "PaymentDetails";
	}

	@GetMapping("/studentPayment")
	private String studentPayment(@RequestParam int rollNo, Model model) {
		Student student= ss.getStudentByRollNo(rollNo);
		model.addAttribute("student",ss.getStudentByRollNo(rollNo));
		model.addAttribute("date",LocalDate.now());
		model.addAttribute("time",LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		model.addAttribute("feeTypeList",feesTypeService.getFeesTypeByGradeAndFaculty(student.getGrade(), student.getFaculty()));
		List<Fees>feesDue=fs.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.DUE);
		List<Fees>feesUnpaid=fs.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
		List<Fees>feesFilter = new ArrayList<>();
		feesFilter.addAll(feesDue);
		feesFilter.addAll(feesUnpaid);
		model.addAttribute("fList",feesFilter);

		return "StudentPayment";
	}

	@PostMapping("/payment")
	private String payment(@ModelAttribute Payment payment, RedirectAttributes redirectAttribute) {


//		String[] split =(payment.getFeesType().split(","));
//		for(int i =0; i<split.length ; i++) {
//		System.out.println(split[i]);
//		
//	}
		List<Fees>feesDue=fs.getAllFeesByRollNoAndStatus(payment.getRollNo(), FeesStatus.DUE);
		List<Fees>feesUnpaid=fs.getAllFeesByRollNoAndStatus(payment.getRollNo(), FeesStatus.UNPAID);
		List<Fees>feesFilter = new ArrayList<>();
		feesFilter.addAll(feesDue);
		feesFilter.addAll(feesUnpaid);

		// for multiple feesType input
		double totalPayment=payment.getAmount();
		String[] feesType =(payment.getFeesType().split(","));
		
		for(Fees i : feesFilter) {
			for(int a = 0; a<feesType.length ; a++) {
				System.out.println("workinggg 1");
				if(i.getFeesType().equals(feesType[a])) {
					System.out.println("workinggg 2");
					if(totalPayment>0) {
						System.out.println("working 3");
						if(totalPayment>i.getAmount()) {
							totalPayment=totalPayment-i.getAmount();
							i.setStatus(FeesStatus.PAID);
							fs.updateFees(i);
						    ps.addPayment(payment);

						}
						else if(totalPayment==i.getAmount()) {
							totalPayment=totalPayment-i.getAmount();
							i.setStatus(FeesStatus.PAID);
							fs.updateFees(i);
							ps.addPayment(payment);
						}
						else if(totalPayment<i.getAmount()) {
							i.setAmount(i.getAmount()-totalPayment);
							fs.updateFees(i);
							ps.addPayment(payment);
						}
					}
				}
			}
		}
		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
		return	 "redirect:/studentPayment";


		//		Fees fees=fs.getFeesByRollNoAndStatusAndFeesType(payment.getRollNo(), FeesStatus.DUE, payment.getFeesType());



		//working for single feesType input
		//		for(Fees i : feesFilter) {
		//			if(payment.getFeesType().equals(i.getFeesType())) {
		//				if(payment.getAmount()==i.getAmount()) {
		//					i.setStatus(FeesStatus.PAID);
		//					fs.updateFees(i);
		//					ps.addPayment(payment);
		//				}
		//				else if(payment.getAmount()<i.getAmount()) {
		//					i.setAmount(i.getAmount()-payment.getAmount());
		//					fs.updateFees(i);
		//					ps.addPayment(payment);
		//
		//				}
		//			}
		//		}
		//		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
		//		return	 "redirect:/studentPayment";



		//working for single feesType INput
		//		Fees fees=fs.getFeesByRollNoAndStatusAndFeesType(payment.getRollNo(), FeesStatus.DUE, payment.getFeesType());
		//		if(payment.getAmount()==fees.getAmount()) {
		//			fees.setStatus(FeesStatus.PAID);
		//			fs.updateFees(fees);
		//		}
		//		else if(payment.getAmount()<fees.getAmount()) {
		//			fees.setAmount(fees.getAmount()-payment.getAmount());
		//			fs.updateFees(fees);
		//		}
		//		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
		//		return	 "redirect:/studentPayment";
	}
}
