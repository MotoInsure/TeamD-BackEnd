package com.ibm.motoInsure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.motoInsure.entity.Admin;
import com.ibm.motoInsure.service.AdminService;
/**
 * <p>Admin Controller is implementing add admin method</p>
 * @author Shruti Mittal
 * @since 18-04-2021
 */

@RestController
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private AdminService as;
	/**
	 * 
	 * @param admin
	 * @return confirmation of addition of admin
	 */
	@PostMapping(value="/addAdmin", consumes="application/json")
	public String addAdmin(@RequestBody Admin admin) {
		as.addAdmin(admin);
		return "Admin added.";
	}
}
