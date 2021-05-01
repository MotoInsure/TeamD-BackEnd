package com.ibm.motoInsure.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.motoInsure.EncodeDecode.Encryption;
import com.ibm.motoInsure.Exception.InvalidPolicyException;
import com.ibm.motoInsure.Exception.InvalidUserException;
import com.ibm.motoInsure.bean.Login;
import com.ibm.motoInsure.entity.User;
import com.ibm.motoInsure.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired 
	private UserService us;
	
	@PostMapping(value="/addUser",consumes="application/json")
	public String addUser(@RequestBody User user) {		
		us.addUser(user);
		return "User added.";
	}
	@GetMapping(value="/forgotPwd/{uname}")
	public String getPassword(@PathVariable String uname) throws InvalidUserException {
		Encryption encrypter = Encryption.getEncrypter();
		return encrypter.DecodePassword(us.forgotPassword(uname));
	}
	@PostMapping(value="/auth", consumes="application/json", produces="application/json")
	public ResponseEntity<?> authentication(@RequestBody Login login, HttpSession session) {
		User u = us.validate(login);
		if(u != null) {	
			session.setAttribute("USER", u); 
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Invalid username or password.",HttpStatus.NOT_FOUND);
		}		 		
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Logged Out.";
	}
	
	@PostMapping(value="/addPolicyToUser/{userId}/{policyId}")
	public ResponseEntity<?> addPolicyToUser(@PathVariable int userId,@PathVariable int policyId, HttpSession session) 
			throws InvalidUserException {
		if(session.getAttribute("USER")!=null){
			return new ResponseEntity<Integer>(us.addPolicyToUser(userId, policyId), HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Sorry! You're not logged in",HttpStatus.NOT_FOUND);		
	}
	@PostMapping(value="/addVehicle/{userId}/{vehicleId}", consumes="application/json")
	public ResponseEntity<?> addUserVehicle(@PathVariable int userId,@PathVariable String vehicleId,HttpSession session) throws InvalidUserException {
		if(session.getAttribute("USER")!=null){
			return new ResponseEntity<Integer>(us.addUserVehicle(userId,vehicleId), HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Sorry! You're not logged in",HttpStatus.NOT_FOUND);
		
	}
}
