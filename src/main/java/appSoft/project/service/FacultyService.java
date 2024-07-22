package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Faculty;

public interface FacultyService {
		void addFaculty(Faculty faculty);
		List<Faculty> getAllFaculty();
		void deleteFacultyById(int id);
		Faculty getFacultyById(int id);
		void updateFaculty(Faculty faculty);
}
