package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Admin;

public interface AdminService {
		void addAdmin(Admin admin);
		Admin findByUsernameAndPassword(String username, String password);
		Admin  findByUsername(String username);

}
