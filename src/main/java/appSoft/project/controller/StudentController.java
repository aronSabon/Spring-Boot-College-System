package appSoft.project.controller;

import java.time.LocalDate;
import java.time.Month;

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
import appSoft.project.model.Student;
import appSoft.project.service.FacultyService;
import appSoft.project.service.FeesService;
import appSoft.project.service.StudentService;


@Controller
public class StudentController {
	@Autowired
	StudentService ss;
	@Autowired
	FacultyService fs;
	@Autowired
	FeesService feesS;
	
	@GetMapping("/addStudent")
	private String studentForm(Model model) {
		model.addAttribute("fList", fs.getAllFaculty());
		return "AddStudent";
	}
	@PostMapping("/addStudent")
	private String addStudent(@ModelAttribute Student student,@RequestParam MultipartFile image) {
		student.setImageName(image.getOriginalFilename());
		ss.addStudent(student);
		
		Fees fees=new Fees();
		fees.setStudentName(student.getFullName());
		fees.setAmount(student.getFaculty().getAdmissionFee());
		fees.setFeesType("Admission Fee");
		fees.setInvoiceDate(student.getAdmissionDate());
		fees.setStatus(FeesStatus.UNPAID);
		fees.setStudentId(student.getId());
		fees.setDueDate(LocalDate.of(2024, Month.OCTOBER, 12));
		feesS.addFees(fees);
		
		return "redirect:/addStudent";
	}
	@GetMapping("/studentList")
	private String studentList(Model model) {
		model.addAttribute("sList", ss.getAllStudent());
		return "StudentList";
	}
	@GetMapping("/deleteStudent")
	private String deleteStudent(@RequestParam int id) {
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
