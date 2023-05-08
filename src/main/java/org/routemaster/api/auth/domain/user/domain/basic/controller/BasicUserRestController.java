package org.routemaster.api.auth.domain.user.domain.basic.controller;

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

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class BasicUserRestController {

    private final BasicUserCommandService basicUserCommandService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<BasicUserReadySecuredResponseVO> register(@RequestBody @Validated BasicUserCreateVO createVO) {
        BasicUserReadySecuredResponseVO securedResponseVO = basicUserCommandService.registerBasicUser(createVO);
        return new ResponseEntity<>(securedResponseVO, HttpStatus.OK);
    }
}
