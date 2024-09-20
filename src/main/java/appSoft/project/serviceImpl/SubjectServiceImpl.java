package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Subject;
import appSoft.project.repository.SubjectRepository;
import appSoft.project.service.SubjectService;


@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	SubjectRepository sr;
		@Override
		public void addSubject(Subject subject) {
			// TODO Auto-generated method stub
			sr.save(subject);
		}

		@Override
		public List<Subject> getAllSubject() {
			// TODO Auto-generated method stub
			return sr.findAll();
		}

		@Override
		public void deleteSubjectById(int id) {
			// TODO Auto-generated method stub
			sr.deleteById(id);
		}

		@Override
		public Subject getSubjectById(int id) {
			// TODO Auto-generated method stub
			return sr.findById(id).get();
		}

		@Override
		public void updateSubject(Subject subject) {
			// TODO Auto-generated method stub
			sr.save(subject);
		}

		@Override
		public List<Subject> getAllSubjecByGrade(String grade) {
			// TODO Auto-generated method stub
			return sr.findByGrade(grade);
		}

}
