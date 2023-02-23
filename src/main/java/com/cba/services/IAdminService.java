package com.cba.services;

import com.cba.entities.Admin;

public interface IAdminService {
	public Admin insertAdmin(Admin admin);

	public Admin updateAdmin(Admin admin);

	public Admin deleteAdmin(int adminId);
}
