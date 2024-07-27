package appSoft.project.model;




import java.time.LocalDate;

import appSoft.project.constant.FeesStatus;
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
public class FeesType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Faculty faculty;
	private String grade;
	private String particulars;
	private Double amount;
	private LocalDate dueDate;
	@Enumerated(EnumType.STRING)
	private FeesStatus status;
}
