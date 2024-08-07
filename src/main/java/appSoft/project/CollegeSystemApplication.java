package appSoft.project;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Salary;
import appSoft.project.model.Teacher;
import appSoft.project.service.SalaryService;
import appSoft.project.service.TeacherService;

@Configuration
@EnableScheduling
@SpringBootApplication
public class CollegeSystemApplication {
	@Autowired
	TeacherService teacherService;
	@Autowired 
	 SalaryService salaryService;



	public static void main(String[] args) {
		SpringApplication.run(CollegeSystemApplication.class, args);
		
	}
	
	
	//10:15 AM on the 15th day of every month 
	//<seconds> <minutes> <hours> <day-of-month> <month> <day-of-week> <year>
//	@Scheduled(cron = "0 0 10 1 * ?",zone = "Asia/Kathmandu")
	@Scheduled(cron = "0 2 14 4 * ?",zone = "Asia/Kathmandu")

	public void scheduleTaskUsingCronExpression() {
		List<Teacher> teacherList= teacherService.getAllTeacher();
		for(Teacher t: teacherList) {
			Salary salary = new Salary();
			salary.setAmount(t.getSalary()*t.getPeriod());
			salary.setFaculty(t.getFaculty());
			salary.setFullName(t.getFullName());
			salary.setGender(t.getGender());
			salary.setGrade(t.getGrade());
			salary.setSubject(t.getSubject());
			salary.setSection(t.getSection());
			salary.setPeriod(t.getPeriod());
			salary.setTeacherId(t.getId());
			salary.setPayDate(LocalDate.now().withDayOfMonth(28));
			salary.setStatus(SalaryStatus.UNPAID);
			salary.setMonth(LocalDate.now().getMonth().toString());
			salaryService.addSalary(salary);
		}
		System.out.println("doneee");
		System.out.println("doneee");

		System.out.println("doneee");

		System.out.println("doneee");

				
	   
	}



	
}
