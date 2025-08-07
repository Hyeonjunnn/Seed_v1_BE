package com.hyeonjunnn.seed_v1_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_no", nullable = false)
    private Long projectNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Column(name = "type", length = 20, nullable = false)
    private String type;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "ended_at", nullable = true)
    private Instant endedAt;

    @Column(name = "consist_of", length = 200, nullable = true)
    private String consistOf;

    @Column(name = "job", length = 100, nullable = true)
    private String job;

    @Column(name = "feature", columnDefinition = "TEXT", nullable = true)
    private String feature;

    @Column(name = "detail", columnDefinition = "TEXT", nullable = true)
    private String detail;

    @Builder.Default
    @Column(name = "is_visible", nullable = false)
    @ColumnDefault("false")
    private Boolean isVisible = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code", nullable = false)
    private Status status;
}
