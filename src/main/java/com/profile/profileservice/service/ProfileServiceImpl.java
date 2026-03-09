package com.profile.profileservice.service;

import com.profile.profileservice.client.AuthServiceClient;
import com.profile.profileservice.dto.*;
import com.profile.profileservice.entity.Genre;
import com.profile.profileservice.entity.Profile;
import com.profile.profileservice.entity.ProfileGenre;
import com.profile.profileservice.repository.GenreRepository;
import com.profile.profileservice.repository.ProfileGenreRepository;
import com.profile.profileservice.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final GenreRepository genreRepository;
    private final ProfileGenreRepository profileGenreRepository;
    private final AuthServiceClient authServiceClient;

    public ProfileServiceImpl(ProfileRepository profileRepository,
                              GenreRepository genreRepository,
                              ProfileGenreRepository profileGenreRepository,
                              AuthServiceClient authServiceClient) {
        this.profileRepository = profileRepository;
        this.genreRepository = genreRepository;
        this.profileGenreRepository = profileGenreRepository;
        this.authServiceClient = authServiceClient;
    }

    @Override
    public ApiResponse<?> getMyFullProfile(Long userId, String email, String role) {
        AuthUserDto authUser = authServiceClient.getUserById(userId, email, role);
        Profile profile = profileRepository.findByUserId(userId).orElse(null);

        FullProfileResponse response = buildFullProfileResponse(authUser, profile);
        return new ApiResponse<>(true, "Full profile fetched successfully", response);
    }

    @Override
    @Transactional
    public ApiResponse<?> updateMyFullProfile(Long userId, String email, String role, FullProfileUpdateRequest request) {
        AuthUserDto authUser = authServiceClient.getUserById(userId, email, role);

        Profile profile = profileRepository.findByUserId(userId).orElse(null);

        if (profile == null) {
            profile = new Profile();
            profile.setUserId(userId);
        }

        profile.setAddress(request.getAddress());
        profile.setDescription(request.getDescription());
        profile.setWebsite(request.getWebsite());
        profile.setFacebookLink(request.getFacebookLink());
        profile.setInstagramLink(request.getInstagramLink());
        profile.setLogoUrl(request.getLogoUrl());
        profile.setNumberOfTypesOfBooks(request.getNumberOfTypesOfBooks());

        Profile savedProfile = profileRepository.save(profile);

        replaceGenres(savedProfile.getId(), request.getGenres());

        Profile latestProfile = profileRepository.findByUserId(userId).orElse(null);
        FullProfileResponse response = buildFullProfileResponse(authUser, latestProfile);

        return new ApiResponse<>(true, "Full profile updated successfully", response);
    }

    private void replaceGenres(Long profileId, List<String> genreNames) {
        profileGenreRepository.deleteByProfileId(profileId);

        if (genreNames == null || genreNames.isEmpty()) {
            return;
        }

        for (String genreName : genreNames) {
            if (genreName == null || genreName.trim().isEmpty()) {
                continue;
            }

            String cleanedName = genreName.trim();

            Genre genre = genreRepository.findByNameIgnoreCase(cleanedName).orElse(null);

            if (genre == null) {
                genre = new Genre();
                genre.setName(cleanedName);
                genre = genreRepository.save(genre);
            }

            ProfileGenre link = new ProfileGenre();
            link.setProfileId(profileId);
            link.setGenreId(genre.getId());

            profileGenreRepository.save(link);
        }
    }

    private List<String> getGenresByProfileId(Long profileId) {
        if (profileId == null) {
            return Collections.emptyList();
        }

        List<ProfileGenre> links = profileGenreRepository.findByProfileId(profileId);

        if (links.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> genreNames = new ArrayList<>();

        for (ProfileGenre link : links) {
            Genre genre = genreRepository.findById(link.getGenreId()).orElse(null);
            if (genre != null) {
                genreNames.add(genre.getName());
            }
        }

        Collections.sort(genreNames);
        return genreNames;
    }

    private FullProfileResponse buildFullProfileResponse(AuthUserDto authUser, Profile profile) {
        FullProfileResponse response = new FullProfileResponse();

        response.setUserId(authUser.getId());
        response.setEmail(authUser.getEmail());
        response.setRole(authUser.getRole());
        response.setCompanyName(authUser.getCompanyName());
        response.setContactNumber(authUser.getContactNumber());
        response.setOwner(authUser.getOwner());

        if (profile != null) {
            response.setAddress(profile.getAddress());
            response.setDescription(profile.getDescription());
            response.setWebsite(profile.getWebsite());
            response.setFacebookLink(profile.getFacebookLink());
            response.setInstagramLink(profile.getInstagramLink());
            response.setLogoUrl(profile.getLogoUrl());
            response.setNumberOfTypesOfBooks(profile.getNumberOfTypesOfBooks());
            response.setGenres(getGenresByProfileId(profile.getId()));
        } else {
            response.setGenres(Collections.emptyList());
        }

        return response;
    }
}