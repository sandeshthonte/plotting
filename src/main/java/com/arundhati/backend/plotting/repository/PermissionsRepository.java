package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.dtos.JPAResponseDTO;
import com.arundhati.backend.plotting.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    Permissions findByPermissionName(String roleName);

    @Query(nativeQuery = true, name = "CustomDTOQuery")
    List<JPAResponseDTO.PermissionsJPADTO> findAllPermissions();



}
