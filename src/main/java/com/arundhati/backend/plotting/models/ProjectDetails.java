package com.arundhati.backend.plotting.models;

import com.arundhati.backend.plotting.dtos.Embeddables;
import com.arundhati.backend.plotting.dtos.RequestDTOs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "project_details")
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String description;
    private String projectIdentifier;
    private Long surveyNo;
    private String layoutName;
//    @ManyToMany(mappedBy = "allocationDetails", fetch = FetchType.LAZY)
//    Set<UserDetails> allocation;

    @Embedded
    private Embeddables.Address address;

    private int isActive = 1;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
//    private

    public ProjectDetails(RequestDTOs.ProjectDetailsDTO projectDetailsDTO){
        this.projectName = projectDetailsDTO.getProjectName();
        this.description = projectDetailsDTO.getDescription();
        this.surveyNo = projectDetailsDTO.getSurveyNo();
        this.layoutName = projectDetailsDTO.getLayoutName();
        this.address = projectDetailsDTO.getAddress();

    }
}
// plot no