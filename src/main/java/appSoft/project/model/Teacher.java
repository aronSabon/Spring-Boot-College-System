package appSoft.project.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	private Date dob;
	private int mobileNumber;
	private Date  joinDate;
	private String qualification;
	private String experience;
	private String email;
	private String username;
	private String password;
	@ManyToMany
	List<Faculty> faculty;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private String imageName;
	private String grade;
	private String section;
	private String subject;



}
