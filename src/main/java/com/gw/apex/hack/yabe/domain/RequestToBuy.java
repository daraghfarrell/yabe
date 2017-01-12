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
public class RequestToBuy extends Request<Buyer, RequestToSell>{

    @Getter
    @Setter
    @ManyToMany(mappedBy = "requestToBuys", cascade = javax.persistence.CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<RequestToSell> requestToSells = new ArrayList<>();

    public void addRequestToSell(RequestToSell requestToSell){
        requestToSells.add(requestToSell);
    }

}
