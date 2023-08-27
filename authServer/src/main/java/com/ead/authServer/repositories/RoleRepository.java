package com.ead.authServer.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ead.authServer.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

}
