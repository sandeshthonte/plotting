package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.dtos.JPAResponseDTO;
import com.arundhati.backend.plotting.models.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
    AccessControl findByPermissionNameAndRoleName(String permissionName, String roleName);

    @Query(nativeQuery = true, name = "AllocationProjectDTOQuery")
    List<JPAResponseDTO.AccessConfigCache> findByRoleAndPermission();

}
