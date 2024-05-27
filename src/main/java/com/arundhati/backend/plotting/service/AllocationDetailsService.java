package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.common.exception.PlottingApiException;
import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.models.*;
import com.arundhati.backend.plotting.repository.AllocationDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AllocationDetailsService {

    @Autowired
    private AllocationDetailsRepository allocationDetailsRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProjectDetailsService projectDetailsService;

    public void addProjectAllocation(RequestDTOs.AllocationDetailsDTO allocationDetailsDto) {
        ProjectDetails projectDetails = projectDetailsService.findById(allocationDetailsDto.getProjectId());
        UserDetails userDetails = userDetailsService.findByUserNameActive(allocationDetailsDto.getUserName());
        if(projectDetails == null || userDetails == null){
            log.info("Project/User not found {}, Project Id {}", allocationDetailsDto.getUserName(), allocationDetailsDto.getProjectId());
            throw new PlottingApiException(ResponseCode.PX_FAILURE_011);
        }
        AllocationDetails allocationDetails = allocationDetailsRepository.findByProjectAndUserDetails(
                allocationDetailsDto.getUserName(), allocationDetailsDto.getProjectId(), allocationDetailsDto.getPlotId());
        if(allocationDetails != null){
            log.info("Allocation found {}, Project Id {}", allocationDetailsDto.getUserName(), allocationDetailsDto.getProjectId());
            return;
        }
        allocationDetails = new AllocationDetails(allocationDetailsDto);
        allocationDetailsRepository.save(allocationDetails);
    }

//    public void addProjectAllocation(AllocationDetailsDTO allocationDetailsDto) {
//        ProjectDetails projectDetails = projectDetailsService.getProjectBySurveyNo(allocationDetailsDto.getProjectSurveyNo());
//        UserDetails userDetails = userDetailsService.getUserByUserName(allocationDetailsDto.getUserName());
//        if(projectDetails == null || userDetails == null){
//            return;
//        }
//
//        AllocationDetails allocationDetails = allocationDetailsRepository.findByUserNameAndProjectSurveyNo(allocationDetailsDto.getUserName(), allocationDetailsDto.getProjectSurveyNo());
//        if(allocationDetails != null){
//            return;
//        }
//        allocationDetails = new AllocationDetails(allocationDetailsDto);
//        allocationDetailsRepository.save(allocationDetails);
//    }

//    public void addProjectAllocation(AllocationDetailsDTO allocationDetailsDto) {
//        ProjectDetails projectDetails = projectDetailsService.getProjectBySurveyNo(allocationDetailsDto.getProjectSurveyNo());
//        UserDetails userDetails = userDetailsService.getUserByUserName(allocationDetailsDto.getUserName());
//        if(projectDetails == null || userDetails == null){
//            return;
//        }
//
//        AllocationDetails allocationDetails = allocationDetailsRepository.findByUserNameAndProjectSurveyNo(allocationDetailsDto.getUserName(), allocationDetailsDto.getProjectSurveyNo());
//        if(allocationDetails != null){
//            return;
//        }
//        allocationDetails = new AllocationDetails(allocationDetailsDto);
//        allocationDetailsRepository.save(allocationDetails);
//    }

//    public List<AccessControlResponseDTO> getACCByPermissionIdRoleId(AccessControlDTO accessControlDTO){
//        return accessControlRepository.getACCByPermissionIdRoleId(accessControlDTO.getPermissionId(), accessControlDTO.getRoleId());
//    }
//
//    public AccessControlResponseDTO getAllACC(){
//        List<Object[]> acc = accessControlRepository.getAllACC();
//        return null;
//    }

}
