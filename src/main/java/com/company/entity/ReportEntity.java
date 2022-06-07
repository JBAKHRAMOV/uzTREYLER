package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "report")
public class ReportEntity extends BaseEntity {
    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "profile_id", nullable = false)
    private String profileId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
}
