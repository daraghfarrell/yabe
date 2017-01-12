package com.gw.apex.hack.yabe.repo;

import com.gw.apex.hack.yabe.domain.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dfarrell on 13/12/2016.
 */
public interface VendorRepo extends CrudRepository<Vendor, String> {

    Vendor findByName(String name);

    Iterable<Vendor>findByNumber(int number);
}
