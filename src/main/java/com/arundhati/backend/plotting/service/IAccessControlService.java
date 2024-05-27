package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.dtos.RequestDTOs;


public interface IAccessControlService {



    public Boolean hasAccess(RequestDTOs.AccessControlDTO accessControlDTO);

}
