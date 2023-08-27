package com.ead.authServer.services;

import java.util.Optional;

import com.ead.authServer.enums.RoleType;
import com.ead.authServer.models.RoleModel;

public interface RoleService {
	Optional<RoleModel> findByRoleName(RoleType roleType);
}
