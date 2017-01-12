package com.gw.apex.hack.yabe.domain;

import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by wmanning on 12/01/2017.
 */
@MappedSuperclass
@Accessors
public abstract class Request<T extends User, R extends Request> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Item item;

    @OneToOne
    private T user;

    @ManyToMany(mappedBy = "request")
    private ArrayList<R> request;
}
