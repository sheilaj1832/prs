package com.prs.business.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository <User, Integer>{

	User findByUserNameAndPassword(String userName, String password);

}
