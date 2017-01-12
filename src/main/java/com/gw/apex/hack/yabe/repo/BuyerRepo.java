package com.gw.apex.hack.yabe.repo;

import com.gw.apex.hack.yabe.domain.Buyer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author dfarrell on 13/12/2016.
 */
public interface BuyerRepo extends CrudRepository<Buyer, String> {
    Buyer findByName(String name0);
}
