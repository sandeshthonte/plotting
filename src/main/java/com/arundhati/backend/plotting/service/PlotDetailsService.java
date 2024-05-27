package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.common.exception.PlottingApiException;
import com.arundhati.backend.plotting.common.utils.ConvertorUtils;
import com.arundhati.backend.plotting.common.utils.StringObjectUtils;
import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.models.PlotDetails;
import com.arundhati.backend.plotting.repository.PlotDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlotDetailsService {

    @Autowired
    private PlotDetailsRepository plotDetailsRepository;

    public void addPlot(RequestDTOs.PlotDetailsDTO plotDetailsDTO){
        StringObjectUtils.sanityCheck(plotDetailsDTO);
        PlotDetails plotDetails = plotDetailsRepository.findByPlotNo(plotDetailsDTO.getPlotNo());
        if(plotDetails != null){
            if(plotDetails.getIsActive() == 1){
                log.info("Project with identifier {} already present", plotDetails.getPlotIdentifier());
                throw new PlottingApiException(ResponseCode.PX_FAILURE_023);
            }
            togglePlot(plotDetails.getPlotIdentifier());
            return;
        }
        plotDetails = ConvertorUtils.convertToClass(plotDetailsDTO, PlotDetails.class);
        plotDetailsRepository.save(plotDetails);
    }

    public void togglePlot(String plotIdentifier){
//        StringObjectUtils.sanityCheck(plotIdentifier);
        PlotDetails plotDetails = findByPlotIdentifier(plotIdentifier);
        plotDetails.setIsActive(1 - plotDetails.getIsActive());
        plotDetailsRepository.save(plotDetails);
    }

    public PlotDetails findByPlotIdentifier(String plotIdentifier){
//        StringObjectUtils.sanityCheck(plotIdentifier);
        PlotDetails plotDetails = plotDetailsRepository.findByPlotIdentifier(plotIdentifier);
        if(plotDetails == null){
            log.info("Plot with identifier {} not found", plotIdentifier);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_024);
        }
        return plotDetails;
    }

    public PlotDetails findByPlotIdentifierAndIsActive(String plotIdentifier){
//        StringObjectUtils.sanityCheck(plotIdentifier);
        PlotDetails plotDetails = plotDetailsRepository.findByPlotIdentifierAndIsActive(plotIdentifier, 1);
        if(plotDetails == null){
            log.info("Active Plot with identifier {} not found", plotIdentifier);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_024);
        }
        return plotDetails;
    }

}
