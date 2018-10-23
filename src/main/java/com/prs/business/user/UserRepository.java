package com.prs.business.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository <User, Integer>{

	//Spring will dissect the direction below by word. It is case sensitive
	// findBy-UserName-And-Password
	User findByUserNameAndPassword(String userName, String password);

}
