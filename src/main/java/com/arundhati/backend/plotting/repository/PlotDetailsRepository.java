package com.arundhati.backend.plotting.repository;

import com.arundhati.backend.plotting.models.PlotDetails;
import com.arundhati.backend.plotting.models.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotDetailsRepository extends JpaRepository<PlotDetails, Long> {
    PlotDetails findByPlotNo(Long plotNo);

    PlotDetails findByPlotIdentifier(String plotIdentifier);

    PlotDetails findByPlotIdentifierAndIsActive(String plotIdentifier, int i);
}
