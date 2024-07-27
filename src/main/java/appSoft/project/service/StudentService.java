package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Faculty;
import appSoft.project.model.Student;

public interface StudentService {
		void addStudent(Student student);
		List<Student> getAllStudent();
		void deleteStudentById(int id);
		Student getStudentById(int id);
		void updateStudent(Student student);
		
		Student getStudentByRollNo(int rollNo);
		List<Student> getStudentByGradeAndFaculty(String grade, Faculty faculty);
}
