package com.example.authservice.rest;

import com.cinquecento.authservicestarter.dto.UserEntityDTO;
import com.cinquecento.authservicestarter.exception.UserEntityNotFoundException;
import com.example.authservice.mapper.UserEntityMapper;
import com.example.authservice.service.impl.UserEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final UserEntityServiceImpl userEntityService;
    private final UserEntityMapper userEntityMapper;

    @Autowired
    public AdminController(UserEntityServiceImpl userEntityService, UserEntityMapper userEntityMapper) {
        this.userEntityService = userEntityService;
        this.userEntityMapper = userEntityMapper;
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<UserEntityDTO> approve(@PathVariable(name = "id") Long id) throws UserEntityNotFoundException {
        UserEntityDTO dto = userEntityMapper.entityToDto(userEntityService.approve(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }
}
