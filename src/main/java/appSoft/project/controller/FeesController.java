package appSoft.project.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
import appSoft.project.model.FeesType;
import appSoft.project.model.Student;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.StudentService;


@Controller
public class FeesController {
	@Autowired
	FeesService fs;
	@Autowired
	StudentService ss;
	
	@Autowired
	private FeesTypeService  feesTypeService;
	
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
		Student student = ss.getStudentByRollNo(fees.getRollNo());
		fees.setGrade(student.getGrade());
		fees.setFaculty(student.getFaculty());
		fs.addFees(fees);
		
		
		return "redirect:/feesList";
	}
	@PostMapping("/addFees")
	private String addFees(@ModelAttribute Fees fees) {
		fees.setStatus(FeesStatus.UNPAID);
		Student student = ss.getStudentByRollNo(fees.getRollNo());
		fees.setGrade(student.getGrade());
		fees.setFaculty(student.getFaculty());
		fs.addFees(fees);
		
		
		return "redirect:/addFees";
	}
	
	@GetMapping("/feesList")
	private String feesList(Model model) {
		List<Fees> feesList=fs.getAllFees();
		for(Fees i : feesList) {
			if(i.getDueDate().isBefore(LocalDate.now())) {
				i.setStatus(FeesStatus.DUE);
			}
			fs.updateFees(i);
		}
		model.addAttribute("fList",feesList );
		
		
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
	private String paymentDetails(@RequestParam int rollNo,Model model) {
		
		
		model.addAttribute("sModel",ss.getStudentByRollNo(rollNo));
		Student student = ss.getStudentByRollNo(rollNo);
	
		model.addAttribute("upfList", fs.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID));
		
		List<Fees>feesFilter=fs.getAllFeesByRollNoAndStatus(rollNo, FeesStatus.UNPAID);
		double subTotal=0;
		int dueViolation=0;
		for(Fees f : feesFilter) {
			subTotal+=f.getAmount();
		}
		for(Fees f : feesFilter) {
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
		

	
//		List<Fees>feesList=fs.getAllFeesByRollNo(rollNo);
//		for(Fees i : feesList) {
//			if(i.getDueDate().isBefore(LocalDate.now())) {
//				i.setStatus(FeesStatus.DUE);
//			}
//		}
		
		model.addAttribute("fList",fs.getAllFeesByRollNo(rollNo));
		model.addAttribute("rollNo",student.getRollNo());
		
		return "PaymentDetails";
	}
	@GetMapping("/studentPayment")
	private String studentPayment(@RequestParam int rollNo, Model model) {
		Student student= ss.getStudentByRollNo(rollNo);
		model.addAttribute("student",ss.getStudentByRollNo(rollNo));
		model.addAttribute("ftList",feesTypeService.getFeesTypeByGradeAndFaculty(student.getGrade(), student.getFaculty()));
		model.addAttribute("date",LocalDate.now());
		model.addAttribute("time",LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		
		model.addAttribute("feeTypeList",feesTypeService.getFeesTypeByGradeAndFaculty(student.getGrade(), student.getFaculty()));
		model.addAttribute("fList",fs.getAllFeesByRollNo(rollNo));

		return null;
	}
}
