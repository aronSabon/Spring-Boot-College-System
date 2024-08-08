package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Admin;
import appSoft.project.repository.AdminRepository;
import appSoft.project.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository;
		@Override
		public void addAdmin(Admin admin) {
			// TODO Auto-generated method stub
			adminRepository.save(admin);
		}
		@Override
		public Admin findByUsernameAndPassword(String username, String password) {
			// TODO Auto-generated method stub
			return adminRepository.findByUsernameAndPassword(username, password);
		}
		@Override
		public Admin findByUsername(String username) {
			// TODO Auto-generated method stub
			return adminRepository.findByUsername(username);
		}


}
