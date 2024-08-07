package appSoft.project.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SalaryPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	private int teacherId;
	private String fullName;
	private double amount;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	private String time;
	private String paidWith;
	private String month;
	
}
