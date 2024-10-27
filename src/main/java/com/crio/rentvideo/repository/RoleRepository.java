package com.crio.rentvideo.repository;

import com.crio.rentvideo.model.Role;
import com.crio.rentvideo.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleType roleType);
}
