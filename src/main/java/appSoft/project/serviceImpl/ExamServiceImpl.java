package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Exam;
import appSoft.project.repository.ExamRepository;
import appSoft.project.service.ExamService;


@Service
public class ExamServiceImpl implements ExamService {
	@Autowired
	ExamRepository er;
		@Override
		public void addExam(Exam exam) {
			// TODO Auto-generated method stub
			er.save(exam);
		}

		@Override
		public List<Exam> getAllExam() {
			// TODO Auto-generated method stub
			return er.findAll();
		}

		@Override
		public void deleteExamById(int id) {
			// TODO Auto-generated method stub
			er.deleteById(id);
		}

		@Override
		public Exam getExamById(int id) {
			// TODO Auto-generated method stub
			return er.findById(id).get();
		}

		@Override
		public void updateExam(Exam exam) {
			// TODO Auto-generated method stub
			er.save(exam);
		}

}
