package org.routemaster.api.auth.domain.user.domain.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.create.BasicUserCreateVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.response.BasicUserReadySecuredResponseVO;
import org.routemaster.api.auth.domain.user.domain.basic.service.BasicUserCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "BasicUser", description = "Route Master 자체 사용자")
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class BasicUserRestController {

    private final BasicUserCommandService basicUserCommandService;

    @Operation(summary = "회원가입", description = "Route Master 자체 사용자 회원가입")
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public Mono<ResponseEntity<BasicUserReadySecuredResponseVO>> register(@RequestBody @Validated BasicUserCreateVO createVO) {
        Mono<BasicUserReadySecuredResponseVO> securedResponseVOMono = basicUserCommandService.registerBasicUser(createVO);
        return securedResponseVOMono.map(securedResponseVO ->
                new ResponseEntity<>(securedResponseVO, HttpStatus.OK));
    }
}
