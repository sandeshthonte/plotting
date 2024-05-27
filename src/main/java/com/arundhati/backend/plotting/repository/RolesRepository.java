package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.dtos.JPAResponseDTO;
import com.arundhati.backend.plotting.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRoleName(String name);
    @Query(value = "SELECT r.*" +
//            ", ac.permission_name as permissionName, p.path_uri as pathUri " +
            "FROM roles r " +
            "WHERE r.is_active = 1 ", nativeQuery = true)
    List<Roles> findAllRoles();

//    default List<Roles> getAccessControlMap() {
//        List<Roles> accessControls = findAllRoles();
//    }

}
