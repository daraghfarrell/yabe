package com.gw.apex.hack.yabe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmanning on 12/01/2017.
 */
@Entity
public class RequestToSell extends Request<Vendor, RequestToBuy>{

    @Getter
    @Setter
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name="SELL_BUY",
//            joinColumns = @JoinColumn(name = "SELL_ID", referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name = "BUY_ID", referencedColumnName = "ID")
//    )
    private List<RequestToBuy> requestToBuys = new ArrayList<>();

    public void addRequestToBuy(RequestToBuy requestToBuy){
        requestToBuys.add(requestToBuy);
    }
}
