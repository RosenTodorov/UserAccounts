package com.user.accounts.services;

import java.util.List;

import com.user.accounts.entities.UserAccount;

public interface IUserService {

	public List<UserAccount> getAll();

	public UserAccount save(UserAccount userAccount);

	public UserAccount findByEmail(String email);

	public boolean delete(UserAccount userAccount);

	public UserAccount findById(long id);

	public void updateUserAccount(UserAccount userAccount, String firstName, String lastName, String email,
			Long dateOfBirth);
}
