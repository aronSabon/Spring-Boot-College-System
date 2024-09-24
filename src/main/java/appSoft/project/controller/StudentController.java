package appSoft.project.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import appSoft.project.model.Fees;
import appSoft.project.model.FeesPayment;
import appSoft.project.model.FeesType;
import appSoft.project.model.Student;
import appSoft.project.repository.StudentRepository;
import appSoft.project.service.FacultyService;
import appSoft.project.service.FeesPaymentService;
import appSoft.project.service.FeesService;
import appSoft.project.service.FeesTypeService;
import appSoft.project.service.StudentService;
import appSoft.project.utils.StudentExcel;


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
	@Autowired
	FeesPaymentService feesPaymentService;
	@Autowired 
	StudentRepository sr;

	
	@GetMapping("/addStudent")
	private String studentForm(Model model) {
		  int maxRollNo = sr.findMaxRollNo();
		    
		    // Increment it to show the next available roll number
		    int rollNo = maxRollNo + 1;

		    // Add it to the model
		    model.addAttribute("rollNo", rollNo);
		model.addAttribute("fList", fs.getAllFaculty());
		return "AddStudent";
	}
	@PostMapping("/addStudent")
	private String addStudent(@ModelAttribute Student student,@RequestParam MultipartFile image,Model model) {
		
		if(!image.isEmpty()) {
			try {
				model.addAttribute("message","image upload success");

				Files.copy(image.getInputStream(), 
				Path.of("src/main/resources/static/studentImages/"+student.getEmail()+".jpg"), 
				StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("image upload failed");
			}
		}
		else {
		model.addAttribute("message","image upload failed");

		}
		
		
		
		student.setImageName(student.getEmail()+".jpg");
		ss.addStudent(student);
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
		feesPaymentService.deleteAllByRollNo(rollNo);
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
		//if u need to update roll no also then add student id to fees and feesPayment or use one to many on fees and feesPayment
		student.setImageName(student.getEmail()+".jpg");
		ss.updateStudent(student);
		
		List<Fees> feesList = feesService.getAllFeesByRollNo(student.getRollNo());
		for(Fees i : feesList) {
			i.setRollNo(student.getRollNo());
			i.setStudentName(student.getFullName());
			i.setFaculty(student.getFaculty());
			i.setGrade(student.getGrade());
			feesService.updateFees(i);
		}
		List<FeesPayment> feesPaymentList = feesPaymentService.getAllByRollNo(student.getRollNo());
		for(FeesPayment i : feesPaymentList) {
			i.setFullName(student.getFullName());
			i.setGrade(student.getGrade());
			i.setRollNo(student.getRollNo());
			feesPaymentService.updatePayment(i);
		}
		return "redirect:/studentList";
	}
	@GetMapping("/studentExcel")
	public  ModelAndView excel() {
		ModelAndView mv =new ModelAndView();
		mv.addObject("studentList", ss.getAllStudent());
		mv.setView(new StudentExcel());
		return mv;
	}
}
