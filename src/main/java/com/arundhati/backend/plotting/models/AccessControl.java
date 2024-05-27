package com.arundhati.backend.plotting.models;

import com.arundhati.backend.plotting.dtos.JPAResponseDTO;
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
@Table(schema = "access_control")
@SqlResultSetMapping(
        name = "AllocationProjectDTOMapping",
        classes = @ConstructorResult(
                targetClass = JPAResponseDTO.AccessConfigCache.class,
                columns = {
                        @ColumnResult(name = "role_name", type = String.class),
                        @ColumnResult(name = "permission_name", type = String.class),
                        @ColumnResult(name = "path_uri", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "AllocationProjectDTOQuery",
        query =  "SELECT ac.role_name, ac.permission_name, p.path_uri " +
                "FROM access_control ac " +
                "INNER JOIN roles r ON ac.role_name = r.role_name " +
                "INNER JOIN permissions p ON ac.permission_name = p.permission_name " +
                "WHERE ac.is_active = 1 " +
                "AND r.is_active = 1 " +
                "AND p.is_active = 1 ",
        resultSetMapping = "AllocationProjectDTOMapping"
)
public class AccessControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permissionName;
    private String roleName;
    private int isActive = 1;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public AccessControl(String permissionName, String roleName){
        this.permissionName = permissionName;
        this.roleName = roleName;
    }
}
