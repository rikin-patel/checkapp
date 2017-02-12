/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.Collection;
import java.util.Date;

import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.repository.GroupRepository;
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
public class GroupServiceBean implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.GroupService#findAll()
	 */
	@Override
	public Collection<Group> findAll() {
		return groupRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.GroupService#findOne(long)
	 */
	@Override
	public Group findOne(long groupId) {
		return groupRepository.findOne(groupId);
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.GroupService#createUser(org.rick.checkappspringboot.ws.model.Group)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, 
	readOnly=false)
	public Group createGroup(Group group) {
		if (group.getGroupId() != null && group.getGroupId() != 0) {
			// Cannot create User with specified userid
			return null;
		}

		group.setCreateDate(new Date());
		Group savedGroup = groupRepository.save(group);
		return savedGroup;
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.GroupService#updateUser(org.rick.checkappspringboot.ws.model.Group)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, 
	readOnly=false)
	public Group updateGroup(Group group) {
		Group groupToUpdate = findOne(group.getGroupId());
		if (groupToUpdate == null) {
			// Cannot update the specified user
			return null;
		}
		Group savedGroup = groupRepository.save(group);
		return savedGroup;
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.GroupService#delete(long)
	 */
	@Override
	public void delete(long id) {
		groupRepository.delete(id);

	}

	@Override
	public Collection<Group> findByOwnerId(long ownerId) {
		return groupRepository.findByOwnerId(ownerId);
	}

}
