package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Faculty;
import appSoft.project.model.SalarySetting;
import appSoft.project.model.Subject;

public interface SalarySettingService {
		void addSalarySetting(SalarySetting salarySetting);
		List<SalarySetting> getAllSalarySetting();
		void deleteSalarySettingById(int id);
		SalarySetting getSalarySettingById(int id);
		void updateSalarySetting(SalarySetting salarySetting);
		
		SalarySetting getSalarySettingByGradeAndFacultyAndSubject(String grade, Faculty faculty, Subject subject);
}
