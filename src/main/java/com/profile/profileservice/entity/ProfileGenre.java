package com.profile.profileservice.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "profile_genres",
    uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "genre_id"})
)
public class ProfileGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    public ProfileGenre() {
    }

    public ProfileGenre(Long profileId, Long genreId) {
        this.profileId = profileId;
        this.genreId = genreId;
    }

    public Long getId() {
        return id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}