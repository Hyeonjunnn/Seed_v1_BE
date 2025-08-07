package com.hyeonjunnn.seed_v1_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private ProjectTechNo no;

    @Embeddable
    public static class ProjectTechNo implements Serializable {
        @Column(name = "project_no")
        private Long projectNo;

        @Column(name = "tech_no")
        private Long techNo;
    }
}
