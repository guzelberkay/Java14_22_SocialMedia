package com.berkayg.controller;

import com.berkayg.dto.request.ActivateCodeRequestDto;
import com.berkayg.dto.request.LoginRequestDto;
import com.berkayg.dto.request.RegisterRequestDto;

import com.berkayg.dto.request.UserUpdateRequestDto;
import com.berkayg.dto.response.RegisterResponseDto;
import com.berkayg.entity.ERole;

import com.berkayg.service.AuthService;
import com.berkayg.utility.JwtTokenManager;
import com.berkayg.utility.TokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.berkayg.constant.EndPoints.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;
    private final TokenManager tokenManager;

    /**
     * Register İşlemleri:
     */
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    /**
     * Login İşlemleri
     */
    @PostMapping(LOGIN)
    public ResponseEntity<String> dologin(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @PutMapping(ACTIVATECODE)
    public ResponseEntity<String> activatecode(@RequestBody ActivateCodeRequestDto dto) {
        return ResponseEntity.ok(authService.activateCode(dto));
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long authId) {
        return ResponseEntity.ok(authService.softDelete(authId));
    }

    @GetMapping("/getToken")
    public ResponseEntity<String> getToken(Long id) {
        return ResponseEntity.ok(jwtTokenManager.createToken(id).get());
    }

    @GetMapping("/getTokenWithRole")
    public ResponseEntity<String> getTokenWithRole(Long id, ERole role) {
        return ResponseEntity.ok(jwtTokenManager.createToken(id, role).get());
    }

    @GetMapping("/getIdFromToken")
    public ResponseEntity<Long> getIdFromToken(String token) {
        return ResponseEntity.ok(jwtTokenManager.getIdFromToken(token).get());
    }

    @GetMapping("/getRoleFromToken")
    public ResponseEntity<String> getRoleFromToken(String token) {
        return ResponseEntity.ok(jwtTokenManager.getRoleFromToken(token).get());
    }





	/*
	@GetMapping(EndPoints.FINDALL)
	public ResponseEntity<List<Auth>> findAll(String token){
		return ResponseEntity.ok(authService.findAll(token));
	}*/
}