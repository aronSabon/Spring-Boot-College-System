package appSoft.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.constant.FeesStatus;
import appSoft.project.constant.SalaryStatus;
import appSoft.project.model.Address;
import appSoft.project.model.Expense;
import appSoft.project.model.Fees;
import appSoft.project.model.Salary;
import jakarta.transaction.Transactional;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
List<Salary> findAllByTeacherId(int id);
List<Salary> findByTeacherIdAndStatus(int id,SalaryStatus status);
@Transactional
List<Salary> deleteAllByTeacherId(int id);

}
