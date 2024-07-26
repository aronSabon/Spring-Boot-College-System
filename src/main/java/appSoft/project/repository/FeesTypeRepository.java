package appSoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Exam;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.Student;

public interface FeesTypeRepository extends JpaRepository<FeesType, Integer> {


}
