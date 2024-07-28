package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Exam;
import appSoft.project.model.Fees;
import appSoft.project.model.Student;
import jakarta.transaction.Transactional;

public interface FeesRepository extends JpaRepository<Fees, Integer> {
	List<Fees> findByRollNo(int rollNo);
	List<Fees> findByRollNoAndStatus(int rollNo,FeesStatus status);
	Fees findByRollNoAndStatusAndFeesType(int rollNo,FeesStatus status,String feesType);

	@Transactional
	List<Fees> deleteAllByRollNo(int rollNo);
	
//	
//	@Transactional
//	@Modifying
//	@Query("Update Cart set  status =:status where user.id =:uid ")
//	void  updateCartStatusByUserId(Integer uid,CartStatus status);
}
