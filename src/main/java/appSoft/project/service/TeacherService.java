package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Teacher;

public interface TeacherService {
		void addTeacher(Teacher teacher);
		List<Teacher> getAllTeacher();
		void deleteTeacherById(int id);
		Teacher getTeacherById(int id);
		void updateTeacher(Teacher teacher);
}
