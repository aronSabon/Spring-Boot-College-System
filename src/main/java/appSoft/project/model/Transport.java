package appSoft.project.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity

public class Transport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String routeName;
	private String vehicleNumber;
	private String driverName;
	private String driverLicense;
	private String contactNumber;
	@OneToOne(cascade = CascadeType.ALL)
	private Address driverAddress;



}
