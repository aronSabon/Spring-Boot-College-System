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
import appSoft.project.model.FeesType;
import appSoft.project.model.Student;
import appSoft.project.service.FacultyService;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.StudentService;


@Controller
public class FeesTypeController {
	@Autowired
	FeesTypeService fts;
	@Autowired
	FacultyService fs;
	@Autowired
	StudentService studentService;
	@Autowired
	FeesService feesService;
	
	@GetMapping("/addFeesType")
	private String feesTypeForm(Model model) {
		model.addAttribute("fList",fs.getAllFaculty());
		return "AddFeesType";
	}
	@PostMapping("/addFeesType")
	private String addFeesType(@ModelAttribute FeesType feesType) {
		feesType.setStatus(FeesStatus.UNPAID);
		fts.addFeesType(feesType);
		List<Student> studentList= studentService.getStudentByGradeAndFaculty(feesType.getGrade(), feesType.getFaculty());
		for(Student i : studentList) {
			Fees fees = new Fees();
			fees.setAmount(feesType.getAmount());
			fees.setDueDate(feesType.getDueDate());
			fees.setFaculty(feesType.getFaculty());
			fees.setFeesType(feesType.getParticulars());
			fees.setGrade(feesType.getGrade());
			fees.setInvoiceDate(LocalDate.now());
			fees.setRollNo(i.getRollNo());
			fees.setStatus(feesType.getStatus());
			fees.setStudentName(i.getFullName());
			feesService.addFees(fees);
		}
		
		return "redirect:/addFeesType";
	}
	
	@GetMapping("/feesTypeList")
	private String feesTypeList(Model model) {
		model.addAttribute("ftList", fts.getAllFeesType());
		return "FeesTypeList";
	}
	@GetMapping("/deleteFeesType")
	private String deleteFeesType(@RequestParam int id) {
		fts.deleteFeesTypeById(id);
		return "redirect:/feesTypeList";
	}
	@GetMapping("/editFeesType")
	private String editFeesType(@RequestParam int id,Model model) {
		model.addAttribute("ftModel",fts.getFeesTypeById(id));
		model.addAttribute("fList",fs.getAllFaculty());

		return "EditFeesType";
	}
	@PostMapping("/updateFeesType")
	private String updateFeesType(@ModelAttribute FeesType feesType) {
		fts.updateFeesType(feesType);
		return "redirect:/feesTypeList";
	}
}
