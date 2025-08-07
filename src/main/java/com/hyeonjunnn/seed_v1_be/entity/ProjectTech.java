package com.hyeonjunnn.seed_v1_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_tech")
public class ProjectTech {
    @EmbeddedId
    private ProjectTechId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectNo") // 필수
    @JoinColumn(name = "project_no")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("techNo") // 필수
    @JoinColumn(name = "tech_no")
    private Tech tech;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ProjectTechId implements Serializable {
        @Column(name = "project_no")
        private Long projectNo;

        @Column(name = "tech_no")
        private Long techNo;
    }
}

