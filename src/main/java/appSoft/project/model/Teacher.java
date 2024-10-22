package appSoft.project.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private String gender;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;
	private String mobileNumber;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate  joinDate;
	private String qualification;
	private String experience;
	private String email;
	private String username;
	private String password;
	@ManyToOne
	private Faculty faculty;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	private String imageName;
	private String grade;
	private String section;
	@ManyToOne
	private Subject subject;
	private double salary;
	private int period;



}
