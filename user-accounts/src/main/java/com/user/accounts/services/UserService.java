package com.user.accounts.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.user.accounts.entities.UserAccount;
import com.user.accounts.repositories.IUserRepository;

@Component
@Service
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	@Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserAccount> getAll() {
		Sort id = Sort.by("id");
		Sort firstNameSort = Sort.by("firstName");
		Sort lastNameSort = Sort.by("lastName");
		Sort emailSort = Sort.by("email");
		Sort dateOfBirth = Sort.by("dateOfBirth");
		Sort groupBySort = id.and(firstNameSort).and(lastNameSort).and(emailSort).and(dateOfBirth);
		return this.userRepository.findAll(groupBySort);
	}

	@Override
	public UserAccount save(UserAccount userAccount) {
		return this.userRepository.save(userAccount);
	}

	@Override
	public UserAccount findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public boolean delete(UserAccount userAccount) {
		if (userAccount == null) {
			return false;
		}
		UserAccount found = this.findByEmail(userAccount.getEmail());
		if (found == null) {
			throw new EntityNotFoundException("Sorry, there is no user with that email!");
		}
		this.userRepository.deleteById(found.getId());
		return true;
	}

	@Override
	public UserAccount findById(long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public void updateUserAccount(UserAccount userAccount, String firstName, String lastName, String email,
			Long dateOfBirth) {
		userAccount.setDateOfBirth(dateOfBirth);
		userAccount.setEmail(email);
		userAccount.setLastName(lastName);
		userAccount.setFirstName(firstName);
	}
}
