package com.profile.profileservice.controller;

import com.profile.profileservice.dto.ApiResponse;
import com.profile.profileservice.dto.FullProfileUpdateRequest;
import com.profile.profileservice.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me/full")
    public ResponseEntity<ApiResponse<?>> getMyFullProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Email") String email,
            @RequestHeader("X-User-Role") String role) {

        return ResponseEntity.ok(profileService.getMyFullProfile(userId, email, role));
    }

    @PutMapping("/me/full")
    public ResponseEntity<ApiResponse<?>> updateMyFullProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Email") String email,
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody FullProfileUpdateRequest request) {

        return ResponseEntity.ok(profileService.updateMyFullProfile(userId, email, role, request));
    }
}