package com.gw.apex.hack.yabe.repo;

import com.gw.apex.hack.yabe.domain.Buyer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author dfarrell on 13/12/2016.
 */

public interface AnyDomainRepo extends CrudRepository<Buyer, String> {

    Buyer findByName(String name);

    Iterable<Buyer>findByNumberGreaterThan(int number);

    Iterable<Buyer> findByNumberLessThan(int number);

    Iterable<Buyer> findByNumberGreaterThanAndNumberLessThan(int num1, int num2);

}
