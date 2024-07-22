package appSoft.project.model;



import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private int mobileNumber;
	private String parentName;
	private int parentMobileNumber;
	private Date dob;
	@OneToOne
	private Faculty faculty;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private String imageName;

}
