package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "janr")
public class JanrEntity extends BaseEntity {
    @Column(unique = true)
    private String name;
}
