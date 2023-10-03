package com.cinqeucento.engineservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Users_Project_Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersProjectRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "users_project_role_upr_id_gen")
    @GenericGenerator(
            name = "users_project_role_upr_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "users_project_role_upr_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "upr_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private UsersProjectRole usersProjectRole;

}
