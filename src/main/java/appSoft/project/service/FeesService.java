package appSoft.project.service;

import java.util.List;

import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Fees;

public interface FeesService {
		void addFees(Fees fees);
		List<Fees> getAllFees();
		void deleteFeesById(int id);
		Fees getFeesById(int id);
		void updateFees(Fees fees);
		List<Fees> getAllFeesByStudentId(int id);
		List<Fees> getAllFeesByStudentIdAndStatus(int id,FeesStatus status);
}
