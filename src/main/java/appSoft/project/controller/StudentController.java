package appSoft.project.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.Student;
import appSoft.project.repository.FeesRepository;
import appSoft.project.service.FacultyService;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.StudentService;


@Controller
public class StudentController {
	@Autowired
	StudentService ss;
	@Autowired
	FacultyService fs;
	@Autowired
	FeesService feesService;
	@Autowired 
	FeesTypeService feesTypeService;

	
	@GetMapping("/addStudent")
	private String studentForm(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		return "AddStudent";
	}
	@PostMapping("/addStudent")
	private String addStudent(@ModelAttribute Student student,@RequestParam MultipartFile image) {
		student.setImageName(image.getOriginalFilename());
		ss.addStudent(student);
		
//		Fees fees=new Fees();
//		fees.setGrade(student.getGrade());
//		fees.setFaculty(student.getFaculty());
//		fees.setStudentName(student.getFullName());
//		fees.setAmount(student.getFaculty().getAdmissionFee());
//		fees.setFeesType("Admission Fee");
//		fees.setInvoiceDate(student.getAdmissionDate());
//		fees.setStatus(FeesStatus.UNPAID);
//		fees.setRollNo(student.getRollNo());
//		fees.setDueDate(LocalDate.of(2024, Month.OCTOBER, 12));
//		feesService.addFees(fees);
		
		
	
		List<FeesType>feesTypeList=feesTypeService.getFeesTypeByGradeAndFaculty(student.getGrade(), student.getFaculty());
	

		for(FeesType i : feesTypeList) {
			System.out.println(i);
			Fees fees1 = new Fees();
			fees1.setGrade(student.getGrade());
			fees1.setFaculty(student.getFaculty());
			fees1.setStudentName(student.getFullName());
			fees1.setAmount(i.getAmount());
			fees1.setAmountPaid(0);
			fees1.setFeesType(i.getParticulars());
			fees1.setInvoiceDate(student.getAdmissionDate());
			fees1.setStatus(i.getStatus());
			fees1.setRollNo(student.getRollNo());
			fees1.setDueDate(i.getDueDate());
			feesService.addFees(fees1);
		}
		
		
		
		
		return "redirect:/addStudent";
	}
	@GetMapping("/studentList")
	private String studentList(Model model) {
		model.addAttribute("sList", ss.getAllStudent());
		return "StudentList";
	}
	@GetMapping("/deleteStudent")
	private String deleteStudent(@RequestParam int id,@RequestParam int rollNo) {
		feesService.deleteAllByRollNo(rollNo);
		ss.deleteStudentById(id);

		return "redirect:/studentList";
	}
	@GetMapping("/editStudent")
	private String editStudent(@RequestParam int id,Model model) {
		model.addAttribute("sModel",ss.getStudentById(id));
		model.addAttribute("fList", fs.getAllFaculty());
		return "EditStudent";
	}
	@PostMapping("/updateStudent")
	private String updateStudent(@ModelAttribute Student student,@RequestParam MultipartFile image) {
		student.setImageName(image.getOriginalFilename());
		ss.updateStudent(student);
		return "redirect:/studentList";
	}
}
