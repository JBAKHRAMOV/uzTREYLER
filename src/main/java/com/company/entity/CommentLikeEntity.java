package com.company.entity;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity extends BaseEntity {
    @Column
    private LikeStatus status;

    @Column(name = "profile_id", nullable = false)
    private String profileId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "comment_id", nullable = false)
    private String commentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private CommentEntity comment;
}
