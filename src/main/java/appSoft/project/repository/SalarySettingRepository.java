package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Exam;
import appSoft.project.model.Faculty;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesType;
import appSoft.project.model.SalarySetting;
import appSoft.project.model.Student;
import appSoft.project.model.Subject;
import jakarta.transaction.Transactional;

public interface SalarySettingRepository extends JpaRepository<SalarySetting, Integer> {
	SalarySetting findByGradeAndFacultyAndSubject(String grade,Faculty faculty,Subject subject);
}
