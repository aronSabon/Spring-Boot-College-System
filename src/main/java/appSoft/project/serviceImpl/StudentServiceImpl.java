package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Faculty;
import appSoft.project.model.Student;
import appSoft.project.repository.StudentRepository;
import appSoft.project.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository sr;
		@Override
		public void addStudent(Student student) {
			// TODO Auto-generated method stub
			sr.save(student);
		}

		@Override
		public List<Student> getAllStudent() {
			// TODO Auto-generated method stub
			return sr.findAll();
		}

		@Override
		public void deleteStudentById(int id) {
			// TODO Auto-generated method stub
			sr.deleteById(id);
		}

		@Override
		public Student getStudentById(int id) {
			// TODO Auto-generated method stub
			return sr.findById(id);
		}

		@Override
		public void updateStudent(Student student) {
			// TODO Auto-generated method stub
			sr.save(student);
		}
		@Override
		public Student getStudentByRollNo(int rollNo) {
			// TODO Auto-generated method stub
			return sr.findByRollNo(rollNo);
		}

		@Override
		public List<Student> getStudentByGradeAndFaculty(String grade, Faculty faculty) {
			// TODO Auto-generated method stub
			return sr.findByGradeAndFaculty(grade, faculty);
		}

}
