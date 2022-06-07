package com.company.entity;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "kino_like")
public class KinoLikeEntity extends BaseEntity {
    @Column
    private LikeStatus status;

    @Column(name = "profile_id", nullable = false)
    private String profileId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "kino_id", nullable = false)
    private String kinoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kino_id", insertable = false, updatable = false)
    private KinoEntity kino;
}
