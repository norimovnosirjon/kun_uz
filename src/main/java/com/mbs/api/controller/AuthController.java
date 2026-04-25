package com.mbs.api.controller;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.LoginRequestDTO;
import com.mbs.api.dto.request.RegistrationDTO;
import com.mbs.api.dto.request.SmsApproveDTO;
import com.mbs.api.dto.response.LoginResponseDTO;
import com.mbs.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 'Bilol Tuxtamurodov' on 07.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Ro'yhatdan o'tish controller api lari")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registration")
    @Operation(summary = "Ro'yhatdan o'tish")
    public ResponseEntity<ApiResponse<String>> registration(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PostMapping("/approve")
    @Operation(summary = "Sms kodni tasdiqlash")
    public ResponseEntity<String> approve(@RequestBody SmsApproveDTO dto) {
        return ResponseEntity.ok(authService.approveSmsCode(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Bor foydalanuvchi tizimga kirishi")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
