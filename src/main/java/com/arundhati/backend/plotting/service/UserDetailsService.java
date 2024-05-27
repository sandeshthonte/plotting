package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.common.exception.PlottingApiException;
import com.arundhati.backend.plotting.common.utils.StringObjectUtils;
import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.models.UserDetails;
import com.arundhati.backend.plotting.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

//    @Autowired
//    private StringObjectUtils stringObjectUtils;

    public void addUser(RequestDTOs.UserDetailsDTO userDetailsDTO){
        StringObjectUtils.sanityCheck(userDetailsDTO);
        UserDetails userDetails = userDetailsRepository.findByUserName(userDetailsDTO.getUsername());
        if(userDetails != null){
            if(userDetails.getIsActive() == 1){
                log.info("User with username {} already present", userDetailsDTO.getUsername());
                throw new PlottingApiException(ResponseCode.PX_FAILURE_021);
            }
            toggleUser(userDetails);
            return;
        }
        userDetails = new UserDetails(userDetailsDTO);
        userDetailsRepository.save(userDetails);
    }

    public void toggleUserApi(String userName){
        StringObjectUtils.sanityCheck(userName);
        UserDetails userDetails = findByUserName(userName);
        toggleUser(userDetails);
    }

    public void toggleUser(UserDetails userDetails){
        userDetails.setIsActive(1 - userDetails.getIsActive());
        userDetailsRepository.save(userDetails);
        log.error("User with username {} toggled to {}", userDetails.getUserName(), userDetails.getIsActive());
    }

    public UserDetails findById(Long id){
        StringObjectUtils.sanityCheck(id);
        return userDetailsRepository.findById(id).orElseGet(() -> {
            log.info("User with id {} not found", id);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_022);
        });
    }

    public UserDetails findByUserName(String username){
        StringObjectUtils.sanityCheck(username);
        UserDetails userDetails = userDetailsRepository.findByUserName(username);
        if(userDetails == null){
            log.info("User with username {} not found", username);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_022);
        }
        return userDetails;
    }

    public UserDetails findByUserNameActive(String username){

        UserDetails userDetails = userDetailsRepository.findByUserNameAndIsActive(username, 1);
        if(userDetails == null){
            log.info("Active User with username {} not found", username);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_022);
        }
        return userDetails;
    }

}
