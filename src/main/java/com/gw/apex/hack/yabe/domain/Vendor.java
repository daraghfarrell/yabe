package com.gw.apex.hack.yabe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

/**
 * @author dfarrell on 13/12/2016.
 */

@Region("vendor")
public class Vendor extends User {

    @Id
    public String name;
    public int number;

    @PersistenceConstructor
    public Vendor(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
