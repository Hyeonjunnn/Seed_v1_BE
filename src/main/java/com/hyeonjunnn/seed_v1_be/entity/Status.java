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

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
public class Status extends BaseEntity{
    @Id
    @Column(name = "status_code", length = 20, nullable = false)
    private String statusCode;

    @Column(name = "content", length = 50, nullable = false)
    private String content;
}
