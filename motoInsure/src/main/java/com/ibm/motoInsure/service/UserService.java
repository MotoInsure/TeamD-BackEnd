/** 
 * 
 */

package com.ibm.motoInsure.service;

import java.util.Optional;

import com.ibm.motoInsure.Exception.InvalidUserException;
import com.ibm.motoInsure.bean.Login;
import com.ibm.motoInsure.entity.User;
/**
 * <p>This service interface is for user containing logic for add and reset password operations</p>
 * @author Jai Baheti
 * @coauthor Thomas Theres
 * @since18-04-2021
 * 
 *
 */
public interface UserService {
	User getUserById(int userId);
	int addUser(User user);
	User getUser(String name);
	User getUserByEmail(String email);
	User forgotPassword(String email) throws InvalidUserException;
	int addPolicyToUser(int id,int policyId) throws InvalidUserException;
	int addUserVehicle(int id,String registrationNo) throws InvalidUserException;
	User validate(Login login);
}
