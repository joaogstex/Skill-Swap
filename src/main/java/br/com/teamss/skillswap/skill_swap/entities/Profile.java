package br.com.teamss.skillswap.skill_swap.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_profiles")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;

    private String imageUrl;

    private String location;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "social_media_links")
    private String socialMediaLinks;

    @Column(name = "availability_status")
    private String availabilityStatus;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    private String interests;

    private String experienceLevel;

    private String educationLevel;
}
