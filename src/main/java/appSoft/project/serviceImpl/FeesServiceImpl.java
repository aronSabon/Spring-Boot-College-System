package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Fees;
import appSoft.project.repository.FeesRepository;
import appSoft.project.service.FeesService;


@Service
public class FeesServiceImpl implements FeesService {
	@Autowired
	FeesRepository fr;
		@Override
		public void addFees(Fees fees) {
			// TODO Auto-generated method stub
			fr.save(fees);
		}

		@Override
		public List<Fees> getAllFees() {
			// TODO Auto-generated method stub
			return fr.findAll();
		}

		@Override
		public void deleteFeesById(int id) {
			// TODO Auto-generated method stub
			fr.deleteById(id);
		}

		@Override
		public Fees getFeesById(int id) {
			// TODO Auto-generated method stub
			return fr.findById(id).get();
		}

		@Override
		public void updateFees(Fees fees) {
			// TODO Auto-generated method stub
			fr.save(fees);
		}

		@Override
		public List<Fees> getAllFeesByRollNo(int rollNo) {
			// TODO Auto-generated method stub
			return fr.findByRollNo(rollNo);
		}

		@Override
		public List<Fees> getAllFeesByRollNoAndStatus(int rollNo,FeesStatus status) {
			// TODO Auto-generated method stub
			return fr.findByRollNoAndStatus(rollNo, status);
		}

		@Override
		public List<Fees> deleteAllByRollNo(int rollNo) {
			// TODO Auto-generated method stub
			return fr.deleteAllByRollNo(rollNo);
		}

		@Override
		public Fees getFeesByRollNoAndStatusAndFeesType(int rollNo, FeesStatus status, String feesType) {
			// TODO Auto-generated method stub
			return fr.findByRollNoAndStatusAndFeesType(rollNo, status, feesType);
		}

}
