package com.ead.authServer.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.authServer.enums.RoleType;
import com.ead.authServer.models.RoleModel;
import com.ead.authServer.repositories.RoleRepository;
import com.ead.authServer.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Optional<RoleModel> findByRoleName(RoleType roleType) {
		return roleRepository.findByRoleName(roleType);
	}

}
