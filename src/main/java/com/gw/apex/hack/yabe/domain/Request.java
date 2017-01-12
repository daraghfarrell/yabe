package com.gw.apex.hack.yabe.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Created by wmanning on 12/01/2017.
 */
@MappedSuperclass
@Accessors
public abstract class Request<U extends User, R extends Request> {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Item item;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private U user;
}
