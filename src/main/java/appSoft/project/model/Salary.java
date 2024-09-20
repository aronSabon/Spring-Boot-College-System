package appSoft.project.model;



import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import appSoft.project.constant.SalaryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int teacherId;
	@ManyToOne
	private Faculty faculty;
	private String grade;
	private String section;
	@ManyToOne
	private Subject subject;
	
	private String fullName;
	private String gender;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate payDate;
	private double amount;
	private int period;
	@Enumerated(EnumType.STRING)
	private SalaryStatus status;
	private double amountPaid;
	private String month;
	

}
