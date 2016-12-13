package com.gw.apex.hack.yabe;

import org.springframework.data.repository.CrudRepository;

/**
 * @author dfarrell on 13/12/2016.
 */

public interface AnyDomainRepo extends CrudRepository<AnyDomainOne, String> {

    AnyDomainOne findByName(String name);

    Iterable<AnyDomainOne>findByNumberGreaterThan(int number);

    Iterable<AnyDomainOne> findByNumberLessThan(int number);

    Iterable<AnyDomainOne> findByNumberGreaterThanAndNumberLessThan(int num1, int num2);

}
