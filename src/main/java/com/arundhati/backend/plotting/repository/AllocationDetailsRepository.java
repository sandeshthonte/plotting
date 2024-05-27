package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.models.AllocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationDetailsRepository extends JpaRepository<AllocationDetails, Long> {

    @Query(value = "SELECT ad.* " +
            "FROM allocation_details ad " +
            "INNER JOIN user_details ud ON ad.user_name = ud.user_name " +
            "INNER JOIN project_details pd ON ad.project_id = pd.id " +
            "WHERE ad.is_active = 1 " +
            "AND ud.is_active = 1 " +
            "AND pd.is_active = 1 " +
            "AND ad.user_name = :username " +
            "AND ad.project_id = :projectId " +
            "AND ad.plot_id = :plotId ", nativeQuery = true)

    AllocationDetails findByProjectAndUserDetails(String username, Long projectId, Long plotId);
}
