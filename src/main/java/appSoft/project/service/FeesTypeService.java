package appSoft.project.service;

import java.util.List;

import appSoft.project.model.FeesType;

public interface FeesTypeService {
		void addFeesType(FeesType feesType);
		List<FeesType> getAllFeesType();
		void deleteFeesTypeById(int id);
		FeesType getFeesTypeById(int id);
		void updateFeesType(FeesType feesType);
}
