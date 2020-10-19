package com.user.accounts.controllers;

import java.util.List;

import javax.management.relation.RelationNotFoundException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.accounts.entities.UserAccount;
import com.user.accounts.services.IUserService;

@Validated
@Component
@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final IUserService userService;

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/accounts")
	public ResponseEntity<List<UserAccount>> findAll() {
		logger.info("All user accounts in a sortable grid!");
		List<UserAccount> list = this.userService.getAll();

		if (list.isEmpty()) {
			logger.info("There are no user accounts!");
		}

		logger.info("There are user accounts!");
		return new ResponseEntity<List<UserAccount>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(value = "/account", consumes = "application/json")
	public ResponseEntity<List<UserAccount>> addBook(@Valid @RequestBody List<UserAccount> userAccount) {
		UserAccount saved = userService.save(userAccount.get(0));
		logger.info("User account {} added!", saved.toString());

		logger.info("All user accounts in a sortable grid!");
		List<UserAccount> list = this.userService.getAll();

		return new ResponseEntity<List<UserAccount>>(list, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("/account/{email}")
	public ResponseEntity<List<UserAccount>> delete(@PathVariable(value = "email") String email) {
		UserAccount userAccount = this.userService.findByEmail(email);
		boolean deleted = this.userService.delete(userAccount);

		if (deleted) {
			logger.info("The user account is deleted with email: " + email);

			List<UserAccount> list = this.userService.getAll();
			logger.info("All user accounts in a sortable grid!");

			return new ResponseEntity<List<UserAccount>>(list, new HttpHeaders(), HttpStatus.NO_CONTENT);
		}

		logger.info("There is no user account with that email!");
		return new ResponseEntity<List<UserAccount>>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/account/{id}", consumes = "application/json")
	public ResponseEntity<List<UserAccount>> updateUserAccount(@PathVariable(value = "id") long id,
			@Valid @RequestBody List<UserAccount> userAccountDetails) throws RelationNotFoundException {
		UserAccount userAccount = this.userService.findById(id);
		if (userAccount == null) {
			throw new EntityNotFoundException("Sorry, there is no user account with that id!");
		}

		userService.updateUserAccount(userAccount, userAccountDetails.get(0).getFirstName(),
				userAccountDetails.get(0).getLastName(), userAccountDetails.get(0).getEmail(),
				userAccountDetails.get(0).getDateOfBirth());

		final UserAccount saved = userService.save(userAccount);
		logger.info("User account {} updated!", saved.toString());

		logger.info("All user accounts in a sortable grid!");
		List<UserAccount> list = this.userService.getAll();

		return new ResponseEntity<List<UserAccount>>(list, new HttpHeaders(), HttpStatus.CREATED);
	}
}