package com.example.authservice.rest;

import com.cinquecento.authservicestarter.dto.AuthenticationUserEntityDTO;
import com.cinquecento.authservicestarter.dto.RegistrationUserEntityDTO;
import com.cinquecento.authservicestarter.dto.UserEntityDTO;
import com.cinquecento.authservicestarter.exception.UserEntityNotCreatedException;
import com.cinquecento.authservicestarter.exception.UserEntityNotFoundException;
import com.cinquecento.authservicestarter.exception.UserEntityNotLoginException;
import com.cinquecento.authservicestarter.exception.UserIsNotApprovedException;
import com.cinquecento.jwtstarter.util.JWTUtil;
import com.example.authservice.mapper.UserEntityMapper;
import com.example.authservice.model.UserEntity;
import com.example.authservice.secutiry.UserEntityDetails;
import com.example.authservice.service.impl.UserEntityRegistrationServiceImpl;
import com.example.authservice.service.impl.UserEntityServiceImpl;
import com.example.authservice.util.ErrorMessageBuilder;
import com.example.authservice.util.UserEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${entity-error-code.not-created}")
    private Integer NOT_CREATED;

    @Value("${entity-error-code.not-approved}")
    private Integer NOT_APPROVED;

    @Value("${entity-error-code.not-login}")
    private Integer NOT_LOGIN;
    private final UserEntityMapper userEntityMapper;
    private final UserEntityValidator userEntityValidator;
    private final ErrorMessageBuilder errorMessageBuilder;
    private final UserEntityRegistrationServiceImpl userEntityRegistrationService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final UserEntityServiceImpl userEntityService;

    @Autowired
    public AuthController(UserEntityMapper userEntityMapper,
                          UserEntityValidator userEntityValidator,
                          ErrorMessageBuilder errorMessageBuilder,
                          UserEntityRegistrationServiceImpl userEntityRegistrationService,
                          JWTUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          UserEntityServiceImpl userEntityService) {
        this.userEntityMapper = userEntityMapper;
        this.userEntityValidator = userEntityValidator;
        this.errorMessageBuilder = errorMessageBuilder;
        this.userEntityRegistrationService = userEntityRegistrationService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userEntityService = userEntityService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationUserEntityDTO authUserEntityDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUserEntityDTO.getUsername(), authUserEntityDTO.getPassword());

        UserEntityDetails userEntityDetails = (UserEntityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
             authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new UserEntityNotLoginException(NOT_LOGIN, "Bad credentials.");
        }

        String token = jwtUtil.generateToken(userEntityDetails.getUserEntity().getId(), authUserEntityDTO.getUsername(), userEntityDetails.isEnabled());

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(null);
    }

    @PostMapping(name = "/registration")
    public ResponseEntity<UserEntityDTO> registration(@RequestBody @Valid RegistrationUserEntityDTO regUserEntityDTO,
                                                      BindingResult bindingResult) {
        UserEntity user = userEntityMapper.registrationDtoToEntity(regUserEntityDTO);
        userEntityValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            throw new UserEntityNotCreatedException(NOT_CREATED, errorMessageBuilder.message(bindingResult));

        UserEntityDTO dto = userEntityMapper.entityToDto(userEntityRegistrationService.register(user));

        String token = jwtUtil.generateToken(dto.getId(), dto.getUsername(), false);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(dto);
    }

    @GetMapping("/{id}/is-approved")
    public ResponseEntity<UserEntityDTO> checkApprove(@PathVariable(name = "id") Long id) throws UserEntityNotFoundException {
        UserEntityDTO entity = userEntityMapper.entityToDto(userEntityService.findById(id));

        if (entity.getIsApproved()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(entity);
        } else {
            throw new UserIsNotApprovedException(NOT_APPROVED, "User with id = " + id + " is not approved.");
        }
    }
}
