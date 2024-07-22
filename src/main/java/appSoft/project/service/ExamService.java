package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Exam;

public interface ExamService {
		void addExam(Exam exam);
		List<Exam> getAllExam();
		void deleteExamById(int id);
		Exam getExamById(int id);
		void updateExam(Exam exam);
}
