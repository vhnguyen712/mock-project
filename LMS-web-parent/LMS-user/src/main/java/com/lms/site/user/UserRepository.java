package com.lms.site.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.commom.entity.AuthenticationType;
import com.lms.commom.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User getUserByEmail(String email);
	
	@Query("Select u From User u Where u.email = :email")
	public User findByEmail(@Param("email") String email);

	@Query("Select u From User u Where u.verificationCode = ?1")
	User findByVerificationCode(String code);

	@Query("Update User u Set u.status = true , u.verificationCode = null Where u.id = ?1")
	@Modifying
	public void enable(Integer id);

	@Query(value = "Update User u Set u.failedAttempt = ?1 Where u.email = ?2")
	@Modifying
	void updateFailedAttempt(int newFailedAttempt, String email);

	@Query("Update User u set u.name = ?1 , u.phone = ?2 Where u.id = ?3")
	@Modifying
	void updateProfile(String name, String phone, int id);

	@Query("Update User u set u.pass = ?1 Where u.id = ?2")
	@Modifying
	void changePassword(String password, int id);

	@Query("Update User u Set u.authenticationType = ?2 where u.id = ?1")
	@Modifying
	public void updateAuthenticationType(Integer userId, AuthenticationType type);

}
