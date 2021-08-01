package org.sid.controllers;

import org.sid.entities.AppUser;
import org.sid.repository.AppUserRepository;
import org.sid.security.JwtUtils;
import org.sid.security.UserDetailsImpl;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	 AuthenticationManager authenticationManager;
	@Autowired
	private AccountService accountService;
	@Autowired
	AppUserRepository appuserRepository;
	@Autowired
	JwtUtils jwtUtils;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody  LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getRole();
        AppUser user = appuserRepository.findByEmail(userDetails.getEmail());

       

        JwtResponse response = new JwtResponse();
        response.setRoleName(user.getRole().getRole());
        response.setUserid(user.getId());
     
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		return accountService.saveUser(
				userForm.getEmail(),
				userForm.getPassword(),
				userForm.getFullname());
	}

}

class UserForm{
	private String email;
	private String password;
	private String fullname;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
}

class LoginRequest {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

class JwtResponse {
	private String token;
	private Long userid;
	private String roleName;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}

