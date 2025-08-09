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
@Table(name = "schedule_mapped_category")
public class ScheduleMappedCategory {
    @EmbeddedId
    private ScheduleMappedCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("scheduleNo")
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("scheduleCategoryNo")
    @JoinColumn(name = "schedule_category_no")
    private ScheduleCategory scheduleCategory;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ScheduleMappedCategoryId implements Serializable {
        @Column(name = "schedule_no")
        private Long scheduleNo;

        @Column(name = "schedule_category_no")
        private Long scheduleCategoryNo;
    }
}