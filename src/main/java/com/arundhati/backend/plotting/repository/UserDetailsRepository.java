package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
//    AccessControl findByPermissionIdAndRoleId(Long permissionId, Long roleId);

    UserDetails findByUserName(String userName);
    UserDetails findByUserNameAndIsActive(String username, int isActive);

    UserDetails findByEmailAndPassword(String email, String password);
}
