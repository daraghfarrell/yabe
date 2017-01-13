package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.ItemRepo;
import com.gw.apex.hack.yabe.domain.RequestToSell;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dfarrell on 13/01/2017.
 */
public class SampleDataLoader {

    public void loadRequestToBuy(RequestToBuyRepo requestToBuyRepo, int i) {
        for(; i > 0; i--) {
            String buyerName = "buyer"+i;
            Item item = new Item("Item"+i);

            RequestToBuy rtb = new RequestToBuy();
            rtb.setItem(item);
            rtb.setUser(new Buyer(buyerName));

            if((i%2)==0) { //even numbers
                rtb.addRequestToSell(new RequestToSell());
                rtb.setVendorOffers(true);
            }

            requestToBuyRepo.save(rtb);
        }
    }

    public void loadBuyers(BuyerRepo buyerRepo){
        List<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("buyerOne"));
        buyers.add(new Buyer("buyerTwo"));
        buyers.add(new Buyer("buyerThree"));

        buyerRepo.save(buyers);
    }

    public void loadItems(ItemRepo itemRepo){
        List<Item> items = new ArrayList<>();
        items.add(new Item("ItemOne"));
        items.add(new Item("ItemTwo"));
        items.add(new Item("ItemThree"));

        itemRepo.save(items);
    }
}
