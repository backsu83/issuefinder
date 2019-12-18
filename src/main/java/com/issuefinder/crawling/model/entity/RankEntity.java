package com.issuefinder.crawling.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "stock_rank",
        uniqueConstraints=
        @UniqueConstraint(
                name = "unique_collect",
                columnNames={"company_code","collect_day", "refer", "resource_type"}
                )
)
public class RankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "collect_day")
    private LocalDate collectDay;

    @Column(name = "refer")
    private String refer;

    @Column(name = "resource_type")
    private Integer resourceType;

    @Column(name = "score")
    private Integer score;

    @Column(name = "create_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at" , columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
