/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.Collection;

import org.rick.checkappspringboot.ws.model.User;
import org.rick.checkappspringboot.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pateriki
 * 
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, 
				readOnly=true)
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rick.checkappspringboot.ws.service.UserService#findAll()
	 */
	@Override
	public Collection<User> findAll() {
		Collection<User> userData = userRepository.findAll();
		return userData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rick.checkappspringboot.ws.service.UserService#findOne(long)
	 */
	@Override
	public User findOne(long userId) {
		User user = userRepository.findOne(userId);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rick.checkappspringboot.ws.service.UserService#createUser(org.rick
	 * .checkappspringboot.ws.model.User)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, 
					readOnly=false)
	public User createUser(User user) {
		if (user.getUserId() != null && user.getUserId() != 0) {
			// Cannot create User with specified userid
			return null;
		}

		User savedUser = userRepository.save(user);
		return savedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rick.checkappspringboot.ws.service.UserService#updateUser(org.rick
	 * .checkappspringboot.ws.model.User)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, 
	readOnly=false)
	public User updateUser(User user) {
		User userToUpdate = findOne(user.getUserId());
		if (userToUpdate == null) {
			// Cannot update the specified user
			return null;
		}
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rick.checkappspringboot.ws.service.UserService#delete(long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, 
	readOnly=false)
	public void delete(long id) {
		userRepository.delete(id);

	}

}
