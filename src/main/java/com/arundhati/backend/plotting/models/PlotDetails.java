package com.arundhati.backend.plotting.models;

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
@Table(schema = "plot_details")
public class PlotDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plotName;
    private String description;
    private String plotIdentifier;
    private Long plotNo;
    private Long projectId;
    private Double squareFeet;
    private Double squareMeters;
//    private Long
    private String layoutName;
    private int isActive = 1;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
//    private

}