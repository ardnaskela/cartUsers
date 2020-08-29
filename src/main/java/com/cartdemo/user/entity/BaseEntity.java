package com.cartdemo.user.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity  {
    @Id
    @GeneratedValue
    protected Long id;
}
