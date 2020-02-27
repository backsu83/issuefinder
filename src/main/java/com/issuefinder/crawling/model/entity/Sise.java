package com.issuefinder.crawling.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stock_sise")
public class Sise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "collect_day")
    private LocalDate collectDay;

    @Column(name = "refer")
    private String refer;

    @Column(name = "closing_price")
    private Integer closingPrice;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createAt;

    @Column(name = "update_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updateAt;
}
