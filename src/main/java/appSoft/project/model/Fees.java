package appSoft.project.model;



import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import appSoft.project.constant.FeesStatus;
import jakarta.persistence.CascadeType;
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
public class Fees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rollNo;
	private String studentName;
	private String feesType;
	private double amount;
	@DateTimeFormat(iso =  ISO.DATE)
	private LocalDate invoiceDate;
	@DateTimeFormat(iso =  ISO.DATE)
	private LocalDate dueDate;
	
	@Enumerated(EnumType.STRING)
	private FeesStatus status;
	@ManyToOne
	private Faculty faculty;
	private String grade;
	private double amountPaid;
	
	
	

}
