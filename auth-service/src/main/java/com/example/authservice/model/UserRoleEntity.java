package com.example.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//@Entity
//@Table(name = "UserRoles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private List<UserEntity> users;

    public UserRoleEntity(String name) {
        this.name = name;
    }
}
