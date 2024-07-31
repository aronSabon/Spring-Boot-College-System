package appSoft.project.model;





import appSoft.project.constant.FeesStatus;
import appSoft.project.constant.SalaryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class SalarySetting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Faculty faculty;
	private String grade;
	@OneToOne
	private Subject subject;
	private Double amount;

}
