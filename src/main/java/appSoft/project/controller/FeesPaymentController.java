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
import appSoft.project.model.FeesPayment;
import appSoft.project.model.Student;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.service.StudentService;

@Controller
public class FeesPaymentController {
	@Autowired
	FeesService feesService;
	@Autowired
	StudentService studentService;

	@Autowired
	private FeesTypeService  feesTypeService;
	@Autowired
	private FeesPaymentService feesPaymentService;



	@GetMapping("/paymentDetails")
	private String paymentDetails(@RequestParam int rollNo,Model model) {
		model.addAttribute("feesList",feesService.getAllFeesByRollNo(rollNo));
		
		
		List<Fees>feesDue=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.DUE);
		List<Fees>feesUnpaid=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
		List<Fees>feesFilter = new ArrayList<>();
		feesFilter.addAll(feesDue);
		feesFilter.addAll(feesUnpaid);
		
		model.addAttribute("duesFilteredList", feesFilter);
System.out.println(feesFilter);
System.out.println(feesFilter);
System.out.println(feesFilter);
System.out.println(feesFilter);

		double subTotal=0;
		double discount=0;
		for(Fees f : feesFilter) {
			subTotal+=f.getAmount();
		}
		double total=subTotal-discount;
		
		Student student = studentService.getStudentByRollNo(rollNo);

		model.addAttribute("sModel",student);
		model.addAttribute("subTotal",subTotal);
		model.addAttribute("total",total);
		model.addAttribute("rollNo",student.getRollNo());
		model.addAttribute("paymentList",feesPaymentService.getAllByRollNo(rollNo));

		
		return "FeesPaymentDetails";
	}

	@GetMapping("/studentPayment")
	private String studentPayment(@RequestParam int rollNo, Model model) {
		Student student= studentService.getStudentByRollNo(rollNo);
		model.addAttribute("student",studentService.getStudentByRollNo(rollNo));
		model.addAttribute("date",LocalDate.now());
		model.addAttribute("time",LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		model.addAttribute("feeTypeList",feesTypeService.getFeesTypeByGradeAndFaculty(student.getGrade(), student.getFaculty()));
		List<Fees>feesDue=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.DUE);
		List<Fees>feesUnpaid=feesService.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
		List<Fees>feesFilter = new ArrayList<>();
		feesFilter.addAll(feesDue);
		feesFilter.addAll(feesUnpaid);
		
		
		model.addAttribute("fList",feesFilter);

		return "StudentPayment";
	}

	@PostMapping("/payment")
	private String feesPayment(@ModelAttribute FeesPayment feesPayment, RedirectAttributes redirectAttribute) {


//		String[] split =(payment.getFeesType().split(","));
//		for(int i =0; i<split.length ; i++) {
//		System.out.println(split[i]);
//		
//	}
		List<Fees>feesDue=feesService.getAllFeesByRollNoAndStatus(feesPayment.getRollNo(), FeesStatus.DUE);
		List<Fees>feesUnpaid=feesService.getAllFeesByRollNoAndStatus(feesPayment.getRollNo(), FeesStatus.UNPAID);
		List<Fees>feesFilter = new ArrayList<>();
		feesFilter.addAll(feesDue);
		feesFilter.addAll(feesUnpaid);

		// for multiple feesType input
		double totalPayment=feesPayment.getAmount();
		String[] feesType =(feesPayment.getFeesType().split(","));
		
		for(Fees i : feesFilter) {
			for(int a = 0; a<feesType.length ; a++) {
				if(i.getFeesType().equals(feesType[a])) {
					if(totalPayment>0) {
						if(totalPayment>(i.getAmount()-i.getAmountPaid())) {
							FeesPayment feesp = new FeesPayment();
							feesp.setAmount(i.getAmount()-i.getAmountPaid());
							feesp.setDate(feesPayment.getDate());
							feesp.setFullName(feesPayment.getFullName());
							feesp.setFeesType(feesType[a]);
							feesp.setPaidWith(feesPayment.getPaidWith());
							feesp.setRollNo(feesPayment.getRollNo());
							feesp.setTime(feesPayment.getTime());
							feesp.setGrade(feesFilter.get(0).getGrade());
							
							
						    feesPaymentService.addPayment(feesp);
							totalPayment=totalPayment-(i.getAmount()-i.getAmountPaid());
							i.setStatus(FeesStatus.PAID);
							i.setAmountPaid(i.getAmountPaid()+(i.getAmount()-i.getAmountPaid()));
							feesService.updateFees(i);
						}
						else if(totalPayment==(i.getAmount()-i.getAmountPaid()))  {
							FeesPayment feesp = new FeesPayment();
							feesp.setAmount(i.getAmount()-i.getAmountPaid());
							feesp.setDate(feesPayment.getDate());
							feesp.setFullName(feesPayment.getFullName());
							feesp.setFeesType(feesType[a]);
							feesp.setPaidWith(feesPayment.getPaidWith());
							feesp.setRollNo(feesPayment.getRollNo());
							feesp.setTime(feesPayment.getTime());
							feesp.setGrade(feesFilter.get(0).getGrade());

							
							feesPaymentService.addPayment(feesp);
							totalPayment=totalPayment-(i.getAmount()-i.getAmountPaid());
							i.setStatus(FeesStatus.PAID);
							i.setAmountPaid(i.getAmountPaid()+(i.getAmount()-i.getAmountPaid()));

							feesService.updateFees(i);
						}
						else if(totalPayment<(i.getAmount()-i.getAmountPaid()))  {  
							FeesPayment feesp = new FeesPayment();
							feesp.setAmount(totalPayment);
							feesp.setDate(feesPayment.getDate());
							feesp.setFullName(feesPayment.getFullName());
							feesp.setFeesType(feesType[a]);
							feesp.setPaidWith(feesPayment.getPaidWith());
							feesp.setRollNo(feesPayment.getRollNo());
							feesp.setTime(feesPayment.getTime());
							feesp.setGrade(feesFilter.get(0).getGrade());

							
							feesPaymentService.addPayment(feesp);
							i.setAmountPaid(i.getAmountPaid()+totalPayment);
							feesService.updateFees(i);
							totalPayment=totalPayment-totalPayment;

						}
					}
				}
			}
		}
		redirectAttribute.addAttribute("rollNo",feesPayment.getRollNo());
		return	 "redirect:/studentPayment";


		//		Fees fees=feesService.getFeesByRollNoAndStatusAndFeesType(payment.getRollNo(), FeesStatus.DUE, payment.getFeesType());



		//working for single feesType input
		//		for(Fees i : feesFilter) {
		//			if(payment.getFeesType().equals(i.getFeesType())) {
		//				if(payment.getAmount()==i.getAmount()) {
		//					i.setStatus(FeesStatus.PAID);
		//					feesService.updateFees(i);
		//					ps.addPayment(payment);
		//				}
		//				else if(payment.getAmount()<i.getAmount()) {
		//					i.setAmount(i.getAmount()-payment.getAmount());
		//					feesService.updateFees(i);
		//					ps.addPayment(payment);
		//
		//				}
		//			}
		//		}
		//		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
		//		return	 "redirect:/studentPayment";



		//working for single feesType INput
		//		Fees fees=feesService.getFeesByRollNoAndStatusAndFeesType(payment.getRollNo(), FeesStatus.DUE, payment.getFeesType());
		//		if(payment.getAmount()==fees.getAmount()) {
		//			fees.setStatus(FeesStatus.PAID);
		//			feesService.updateFees(fees);
		//		}
		//		else if(payment.getAmount()<fees.getAmount()) {
		//			fees.setAmount(fees.getAmount()-payment.getAmount());
		//			feesService.updateFees(fees);
		//		}
		//		redirectAttribute.addAttribute("rollNo",payment.getRollNo());
		//		return	 "redirect:/studentPayment";
	}
}
