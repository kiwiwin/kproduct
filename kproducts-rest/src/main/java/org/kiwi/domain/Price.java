package org.kiwi.domain;

import java.sql.Timestamp;

public class Price {
    int id;
    private int price;
    private String modifiedBy;
    private Timestamp modifiedTimestamp;

    public Price(int price, String modifiedBy, Timestamp modifiedTimestamp) {
        this.price = price;
        this.modifiedBy = modifiedBy;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Timestamp getModifiedTimestamp() {
        return modifiedTimestamp;
    }
}
