package com.ead.authServer.repositories;

import com.ead.authServer.models.UserModel;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    
    @EntityGraph(attributePaths = "roles", type= EntityGraph.EntityGraphType.FETCH)
    Optional<UserModel> findByUserName(String userName);
}
