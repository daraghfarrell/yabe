package com.gw.apex.hack.yabe;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * @author dfarrell on 13/12/2016.
 */

@Region
public class AnyDomainOne {

    @Id
    public String stringId;
    public int intId;

    @Override
    public String toString() {
        return "AnyDomainOne{" +
                "stringId='" + stringId + '\'' +
                ", intId=" + intId +
                '}';
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }
}
