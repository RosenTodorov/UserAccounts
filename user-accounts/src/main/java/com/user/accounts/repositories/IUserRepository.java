package com.user.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.accounts.entities.UserAccount;

@Repository
public interface IUserRepository extends JpaRepository<UserAccount, Long> {

	UserAccount findByEmail(String email);

	UserAccount findById(long id);
}
