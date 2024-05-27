package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.common.exception.PlottingApiException;
import com.arundhati.backend.plotting.common.utils.ConvertorUtils;
import com.arundhati.backend.plotting.common.utils.StringObjectUtils;
import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.models.ProjectDetails;
import com.arundhati.backend.plotting.repository.ProjectDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectDetailsService {

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    public void addProject(RequestDTOs.ProjectDetailsDTO projectDetailsDTO){
        ProjectDetails projectDetails = projectDetailsRepository.findByProjectIdentifier(projectDetailsDTO.getProjectIdentifier());
        if(projectDetails != null){
            if(projectDetails.getIsActive() == 1){
                log.info("Project with identifier {} already present", projectDetails.getProjectIdentifier());
                throw new PlottingApiException(ResponseCode.PX_FAILURE_023);
            }
            toggleProject(projectDetails.getProjectIdentifier());
            return;
        }
        projectDetails = ConvertorUtils.convertToClass(projectDetailsDTO, ProjectDetails.class);
        projectDetailsRepository.save(projectDetails);
    }

    public void toggleProject(String projectIdentifier){
        StringObjectUtils.sanityCheck(projectIdentifier);
        ProjectDetails projectDetails = findByProjectIdentifier(projectIdentifier);
        projectDetails.setIsActive(1 - projectDetails.getIsActive());
        projectDetailsRepository.save(projectDetails);
    }

    public ProjectDetails findByProjectIdentifier(String projectIdentifier){
        StringObjectUtils.sanityCheck(projectIdentifier);
        ProjectDetails projectDetails = projectDetailsRepository.findByProjectIdentifier(projectIdentifier);
        if(projectDetails == null){
            log.info("Active Project with identifier {} not found", projectIdentifier);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_024);
        }
        return projectDetails;
    }

    public ProjectDetails findById(Long id){
        StringObjectUtils.sanityCheck(id);
        return projectDetailsRepository.findById(id).orElseGet(() -> {
            log.info("Project with id {} not found", id);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_024);
        });
    }

    public ProjectDetails findByProjectIdentifierAndIsActive(String projectIdentifier){
//        StringObjectUtils.sanityCheck(projectIdentifier);
        ProjectDetails projectDetails = projectDetailsRepository.findByProjectIdentifierAndIsActive(projectIdentifier, 1);
        if(projectDetails == null){
            log.info("Active Project with identifier {} not found", projectIdentifier);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_024);
        }
        return projectDetails;
    }

}
