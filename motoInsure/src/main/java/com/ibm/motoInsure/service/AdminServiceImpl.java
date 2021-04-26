package com.ibm.motoInsure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.motoInsure.entity.Admin;
import com.ibm.motoInsure.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired 
	private AdminRepository ar;
	
	@Override
	public int addAdmin(Admin admin) {
		ar.save(admin);
		return admin.getId();
	}

}
