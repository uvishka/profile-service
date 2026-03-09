package com.profile.profileservice.service;

import com.profile.profileservice.dto.ApiResponse;
import com.profile.profileservice.dto.FullProfileUpdateRequest;

public interface ProfileService {
    ApiResponse<?> getMyFullProfile(Long userId, String email, String role);
    ApiResponse<?> updateMyFullProfile(Long userId, String email, String role, FullProfileUpdateRequest request);
}