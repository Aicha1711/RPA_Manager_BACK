package org.sid.service;

import org.sid.entities.AppUser;
import org.sid.repository.AppRoleRepository;
import org.sid.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired 
	AppUserRepository appUserRepository;
	@Autowired
	AppRoleRepository appRoleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AppUser loadUserByEmail(String email) {
		return appUserRepository.findByEmail(email);
	}

	@Override
	public AppUser saveUser(String email, String password, String fullname) {
		
		AppUser user=new AppUser();
		user.setEmail(email);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setFullname(fullname);
		user.setRole(appRoleRepository.findByRole("ROLE_USER"));
		return appUserRepository.save(user);
	}

}
