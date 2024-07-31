package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Faculty;
import appSoft.project.model.SalarySetting;
import appSoft.project.model.Subject;
import appSoft.project.repository.SalarySettingRepository;
import appSoft.project.service.SalarySettingService;


@Service
public class SalarySettingServiceImpl implements SalarySettingService {
	@Autowired
	SalarySettingRepository salarySettingRepository;
		@Override
		public void addSalarySetting(SalarySetting salarySetting) {
			// TODO Auto-generated method stub
			salarySettingRepository.save(salarySetting);
		}

		@Override
		public List<SalarySetting> getAllSalarySetting() {
			// TODO Auto-generated method stub
			return salarySettingRepository.findAll();
		}

		@Override
		public void deleteSalarySettingById(int id) {
			// TODO Auto-generated method stub
			salarySettingRepository.deleteById(id);
		}

		@Override
		public SalarySetting getSalarySettingById(int id) {
			// TODO Auto-generated method stub
			return salarySettingRepository.findById(id).get();
		}

		@Override
		public void updateSalarySetting(SalarySetting salarySetting) {
			// TODO Auto-generated method stub
			salarySettingRepository.save(salarySetting);
		}

		@Override
		public SalarySetting getSalarySettingByGradeAndFacultyAndSubject(String grade, Faculty faculty,
				Subject subject) {
			// TODO Auto-generated method stub
			return salarySettingRepository.findByGradeAndFacultyAndSubject(grade, faculty, subject);
		}

	

}
