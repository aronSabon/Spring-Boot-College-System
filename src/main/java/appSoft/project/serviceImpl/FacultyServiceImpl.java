package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Faculty;
import appSoft.project.repository.FacultyRepository;
import appSoft.project.service.FacultyService;


@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	FacultyRepository fr;
		@Override
		public void addFaculty(Faculty faculty) {
			// TODO Auto-generated method stub
			fr.save(faculty);
		}

		@Override
		public List<Faculty> getAllFaculty() {
			// TODO Auto-generated method stub
			return fr.findAll();
		}

		@Override
		public void deleteFacultyById(int id) {
			// TODO Auto-generated method stub
			fr.deleteById(id);
		}

		@Override
		public Faculty getFacultyById(int id) {
			// TODO Auto-generated method stub
			return fr.findById(id).get();
		}

		@Override
		public void updateFaculty(Faculty faculty) {
			// TODO Auto-generated method stub
			fr.save(faculty);
		}

}
