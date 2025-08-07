package com.hyeonjunnn.seed_v1_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boardCategory")
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardCategory_no", nullable = false)
    private Long boardCategoryNo;

    @Column(name = "name", length = 20, nullable = false, unique = true)
    private String name;

    @Builder.Default
    @Column(name = "parent_board_category_no", nullable = true)
    @ColumnDefault("null")
    private Long parentBoardCategoryNo = null;

    @Builder.Default
    @Column(name = "is_visible", nullable = false)
    @ColumnDefault("false")
    private boolean isVisible = false;
}