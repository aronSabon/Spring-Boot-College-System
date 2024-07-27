package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Faculty;
import appSoft.project.model.FeesType;
import appSoft.project.repository.FeesTypeRepository;
import appSoft.project.service.FeesTypeService;


@Service
public class FeesTypeServiceImpl implements FeesTypeService {
	@Autowired
	FeesTypeRepository ftr;
		@Override
		public void addFeesType(FeesType feesType) {
			// TODO Auto-generated method stub
			ftr.save(feesType);
		}

		@Override
		public List<FeesType> getAllFeesType() {
			// TODO Auto-generated method stub
			return ftr.findAll();
		}

		@Override
		public void deleteFeesTypeById(int id) {
			// TODO Auto-generated method stub
			ftr.deleteById(id);
		}

		@Override
		public FeesType getFeesTypeById(int id) {
			// TODO Auto-generated method stub
			return ftr.findById(id).get();
		}

		@Override
		public void updateFeesType(FeesType feesType) {
			// TODO Auto-generated method stub
			ftr.save(feesType);
		}

		@Override
		public List<FeesType> getFeesTypeByGradeAndFaculty(String grade, Faculty faculty) {
			// TODO Auto-generated method stub
			return ftr.findByGradeAndFaculty(grade, faculty);
		}

}
