package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "kino_janr")
public class KinoJanrEntity extends BaseEntity {

    @Column(name = "kino_id", nullable = false)
    private String kinoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kino_id", insertable = false, updatable = false)
    private KinoEntity kino;

    @Column(name = "janr_id", nullable = false)
    private String janrId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "janr_id", insertable = false, updatable = false)
    private JanrEntity janr;

}
