package com.arundhati.backend.plotting.models;

import com.arundhati.backend.plotting.config.EncryptionConverter;
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
@Table(schema = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Convert(converter = EncryptionConverter.class)
    private String email;
    @Convert(converter = EncryptionConverter.class)
    private String mobile;
    private Long roleId;
    @Convert(converter = EncryptionConverter.class)
    private String password;
//    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinTable(name = "allocation_details",
//    joinColumns = @JoinColumn(name = "user_details_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "project_details_id", referencedColumnName = "id")
//    )
//    Set<ProjectDetails> allocationDetails;

    private int isActive = 1;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public UserDetails(RequestDTOs.UserDetailsDTO userDetailsDTO){
        this.userName = userDetailsDTO.getUsername();
        this.email = userDetailsDTO.getEmail();
        this.mobile = userDetailsDTO.getMobile();
        this.roleId = userDetailsDTO.getRoleId();
        this.password = userDetailsDTO.getPassword();
    }

}
