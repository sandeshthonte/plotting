package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.models.ProjectDetails;
import com.arundhati.backend.plotting.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Long> {
    ProjectDetails findBySurveyNo(Long surveyNo);

    ProjectDetails findByIdAndIsActive(Long id, int i);

    ProjectDetails findByProjectIdentifier(String projectIdentifier);

    ProjectDetails findByProjectIdentifierAndIsActive(String projectIdentifier, int i);
}
