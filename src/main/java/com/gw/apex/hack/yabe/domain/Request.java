package com.gw.apex.hack.yabe.domain;

import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by wmanning on 12/01/2017.
 */
@MappedSuperclass
@Accessors
public abstract class Request<T extends User, R extends Request> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Item item;
    private T user;
    private R[] request;
}