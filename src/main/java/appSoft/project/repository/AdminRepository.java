package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Admin;
import appSoft.project.model.Exam;
import appSoft.project.model.Fees;
import appSoft.project.model.Student;
import jakarta.transaction.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin  findByUsername(String username);
	Admin findByUsernameAndPassword(String username,String Password);
	
}
