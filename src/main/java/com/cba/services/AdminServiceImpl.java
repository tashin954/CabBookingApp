package com.cba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cba.entities.Admin;
import com.cba.exceptions.AdminIdNotFoundException;
import com.cba.repositories.IAdminRepository;

@Service("as")
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminRepository ar;

	@Override
	public Admin insertAdmin(Admin admin) {
		return ar.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		return ar.save(admin);
	}

	@Override
	public Admin deleteAdmin(int adminId)  {
		Admin data = ar.findById(adminId).orElseThrow(() ->  new AdminIdNotFoundException("admin id not found"));
		ar.delete(data);
		return data;
	}
}
