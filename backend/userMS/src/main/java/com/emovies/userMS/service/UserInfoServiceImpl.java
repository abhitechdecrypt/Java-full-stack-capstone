package com.emovies.userMS.service;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emovies.userMS.DAO.UserLoginDTO;
import com.emovies.userMS.Entity.UserInfo;
import com.emovies.userMS.exception.UserAlreadyExistException;
import com.emovies.userMS.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

	private final UserInfoRepository userInfoRepository;
	private final PasswordEncoder passwordEncoder;

	public UserInfoServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
		this.userInfoRepository = userInfoRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserInfo registerUser(UserInfo userInfo) {
	    Random random = new Random();

	    // Check if user with the same email already exists
	    UserInfo existingUserByEmail = userInfoRepository.findByEmail(userInfo.getEmail());
	    // Check if user with the same phone number already exists
	    UserInfo existingUserByPhoneNumber = userInfoRepository.findByPhoneNumber(userInfo.getPhoneNumber());

	    // If either exists, throw an exception
	    if (existingUserByEmail != null || existingUserByPhoneNumber != null) {
	        throw new UserAlreadyExistException(
	            "Email Id or Phone number already registered with us",
	            String.valueOf(HttpStatus.CONFLICT.value())
	        );
	    }

	    // Generate a unique user ID
	    long userIdNumber = 1000000000L + (long) (random.nextDouble() * 9000000000L);
	    String userPrefix = "USER" + userIdNumber;
	    userInfo.setUserID(userPrefix);

	    // Encrypt the password
	    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

	    // Save the new user to the repository
	    return userInfoRepository.save(userInfo);
	}


	/**
	 * @param userLoginDTO
	 * @return
	 */
	@Override
	public UserInfo loginUser(UserLoginDTO userLoginDTO) {
		UserInfo userInfo = null;

		try {
			if (userLoginDTO.getEmail() != null) {
				userInfo = userInfoRepository.findByEmail(userLoginDTO.getEmail());
			} else if (userLoginDTO.getPhoneNumber() != null) {
				userInfo = userInfoRepository.findByPhoneNumber(userLoginDTO.getPhoneNumber());
			}

			if (userInfo != null && passwordEncoder.matches(userLoginDTO.getPassword(), userInfo.getPassword())) {
				return userInfo;
			} else {
				log.warn("Failed login attempt for user: " + userLoginDTO.getEmail() + " or phone number: "
						+ userLoginDTO.getPhoneNumber());
			}
		} catch (Exception e) {
			// Log exception details
			log.error("Error during login attempt", e);
			throw new RuntimeException("Login failed due to a system error");
		}

		return null;
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public boolean deleteUser(String userId) {
		return false;
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public UserInfo findUser(String userId) {
		return null;
	}

	/**
	 * @param userInfo
	 * @param userId
	 * @return
	 */
	@Override
	public UserInfo updateUser(UserInfo userInfo, String userId) {
		return null;
	}

}
