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
@Table(name = "schedule_mapped_category")
public class ScheduleMappedCategory {
    @EmbeddedId
    private ScheduleMappedCategory.ScheduleMappedCategoryNo no;

    @Embeddable
    public static class ScheduleMappedCategoryNo implements Serializable {
        @Column(name = "schedule_no")
        private Long scheduleNo;

        @Column(name = "schedule_category_no")
        private Long scheduleCategoryNo;
    }
}
