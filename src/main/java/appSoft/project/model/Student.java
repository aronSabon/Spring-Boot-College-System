package appSoft.project.model;



import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private String email;
	private int rollNo;
	private String grade;
	private String section;
	private String gender;
	private String mobileNumber;
	private String parentName;
	private String parentMobileNumber;
	@DateTimeFormat(iso =  ISO.DATE)
	private LocalDate dob;
	@DateTimeFormat(iso =  ISO.DATE)
	private LocalDate admissionDate;
	@ManyToOne
	private Faculty faculty;
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	private String imageName;

}
