package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
    @Column(columnDefinition = "text")
    private String content;
    @Column
    private String replyId;

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

    @Column
    private Integer like_count;
    @Column
    private Integer dislike_count;
}
