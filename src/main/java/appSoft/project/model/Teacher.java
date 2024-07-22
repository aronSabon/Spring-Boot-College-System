package appSoft.project.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String email;
	private Date  joinDate;
	private String username;
	private String password;
	private int mobileNumber;
	private String gender;
	@OneToMany
	List<Faculty> faculty;
	private Date dob;
	private String education;
	private String experience;
	@OneToOne
	private Address address;



}
