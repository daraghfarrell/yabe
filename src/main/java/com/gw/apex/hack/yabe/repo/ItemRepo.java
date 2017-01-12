package com.gw.apex.hack.yabe.repo;

import com.gw.apex.hack.yabe.domain.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wmanning on 12/01/2017.
 */
public interface ItemRepo extends CrudRepository<Item, Long> {
}
