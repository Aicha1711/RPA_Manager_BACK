package org.sid.service;

import org.sid.entities.AppUser;

public interface AccountService {
	public AppUser loadUserByEmail(String email);

	public AppUser saveUser(String email,String password,String fullname);
}
