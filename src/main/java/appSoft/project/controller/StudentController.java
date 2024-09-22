package appSoft.project.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
import appSoft.project.model.FeesType;
import appSoft.project.model.Student;
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

	
	@GetMapping("/addStudent")
	private String studentForm(Model model) {
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

		return "redirect:/addStudent";
		}
		
		
		
		student.setImageName(image.getOriginalFilename());
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
		student.setImageName(image.getOriginalFilename());
		ss.updateStudent(student);
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
