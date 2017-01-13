package com.gw.apex.hack.yabe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wmanning on 12/01/2017.
 */
@Entity
public class Item {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    private String name;

    public Item(){}

    public Item(String name){this.name = name;}

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                '}';
    }
}
