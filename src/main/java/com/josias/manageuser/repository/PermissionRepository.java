package com.josias.manageuser.repository;

import com.josias.manageuser.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName(String name);
    Permission findByName(String name);
}
