package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Address;
import appSoft.project.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
List<Salary> findByTeacherId(String teacherId);

}
