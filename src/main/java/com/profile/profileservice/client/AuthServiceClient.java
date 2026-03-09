package com.profile.profileservice.client;

import com.profile.profileservice.dto.AuthUserDto;
import com.profile.profileservice.dto.GenericAuthResponse;
import com.profile.profileservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthServiceClient {

    private final RestTemplate restTemplate;

    @Value("${auth.service.base-url}")
    private String authBaseUrl;

    public AuthServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthUserDto getUserById(Long userId, String email, String role) {
        String url = authBaseUrl + "/api/auth/users/" + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Email", email);
        headers.set("X-User-Role", role);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<GenericAuthResponse<AuthUserDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<GenericAuthResponse<AuthUserDto>>() {}
        );

        GenericAuthResponse<AuthUserDto> body = response.getBody();

        if (body == null || !body.isSuccess() || body.getData() == null) {
            throw new ResourceNotFoundException("User not found in Auth Service");
        }

        return body.getData();
    }
}