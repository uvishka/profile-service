package com.profile.profileservice.repository;

import com.profile.profileservice.entity.ProfileGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileGenreRepository extends JpaRepository<ProfileGenre, Long> {
    List<ProfileGenre> findByProfileId(Long profileId);
    Optional<ProfileGenre> findByProfileIdAndGenreId(Long profileId, Long genreId);
    void deleteByProfileId(Long profileId);
}