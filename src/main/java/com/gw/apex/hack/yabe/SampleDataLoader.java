package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;

/**
 * @author dfarrell on 13/01/2017.
 */
public class SampleDataLoader {

    public void loadRequestToBuy(RequestToBuyRepo requestToBuyRepo) {
        for(int i = 0; i < 10; i++) {
            String buyerName = "buyer4328";
            Item item = new Item();

            RequestToBuy rtb = new RequestToBuy();
            rtb.setItem(item);
            rtb.setUser(new Buyer(buyerName));
            requestToBuyRepo.save(rtb);
        }
    }
}
