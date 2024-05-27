package com.arundhati.backend.plotting.models;

import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.enums.OwnerTypeEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "allocation_details")
public class AllocationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Long projectId;
    private Long plotId;
    @Enumerated(EnumType.STRING)
    private OwnerTypeEnums ownerType;
    private int isActive = 1;
    @CurrentTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public AllocationDetails(RequestDTOs.AllocationDetailsDTO allocationDetailsDto) {
        this.userName = allocationDetailsDto.getUserName();
        this.projectId = allocationDetailsDto.getProjectId();
        this.plotId = allocationDetailsDto.getPlotId();
        this.ownerType = OwnerTypeEnums.valueOf(allocationDetailsDto.getOwnerType());
    }
}
