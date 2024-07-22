package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Subject;

public interface SubjectService {
		void addSubject(Subject subject);
		List<Subject> getAllSubject();
		void deleteSubjectById(int id);
		Subject getSubjectById(int id);
		void updateSubject(Subject subject);
}
