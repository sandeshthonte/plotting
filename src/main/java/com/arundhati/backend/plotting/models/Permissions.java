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
@Table(schema = "permissions")
@SqlResultSetMapping(
        name = "CustomDTOMapping",
        classes = @ConstructorResult(
                targetClass = JPAResponseDTO.PermissionsJPADTO.class,
                columns = {
                        @ColumnResult(name = "permission_name", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "path_uri", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "CustomDTOQuery",
        query = "SELECT permission_name, description, path_uri FROM permissions where is_active = 1 ",
        resultSetMapping = "CustomDTOMapping"
)
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permissionName;
    private String description;
    private String pathUri;
    private int isActive = 1;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
}
