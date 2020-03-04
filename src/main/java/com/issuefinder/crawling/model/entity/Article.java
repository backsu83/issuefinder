package com.issuefinder.crawling.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stock_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "collect_day")
    private LocalDate collectDay;

    @Column(name = "refer")
    private String refer;

    @Column(name = "views")
    private Integer views;

    @Column(name = "sympathy")
    private Integer sympathy;

    @Column(name = "unsympathy")
    private Integer unsympathy;

    @Column(name = "score")
    private Integer score;

    @Column(name = "create_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at" , columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updateAt;
}

