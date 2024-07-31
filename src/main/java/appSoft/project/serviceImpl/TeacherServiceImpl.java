package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Teacher;
import appSoft.project.repository.TeacherRepository;
import appSoft.project.service.TeacherService;


@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherRepository tr;
		@Override
		public void addTeacher(Teacher teacher) {
			// TODO Auto-generated method stub
			tr.save(teacher);
		}

		@Override
		public List<Teacher> getAllTeacher() {
			// TODO Auto-generated method stub
			return tr.findAll();
		}

		@Override
		public void deleteTeacherById(int id) {
			// TODO Auto-generated method stub
			tr.deleteById(id);
		}

		@Override
		public Teacher getTeacherById(int id) {
			// TODO Auto-generated method stub
			return tr.findById(id).get();
		}

		@Override
		public void updateTeacher(Teacher teacher) {
			// TODO Auto-generated method stub
			tr.save(teacher);
		}

		@Override
		public Teacher getTeacherByTeacherId(String teacherId) {
			// TODO Auto-generated method stub
			return tr.findByTeacherId(teacherId);
		}

}
