package org.kiwi.json;

import org.kiwi.domain.Price;

import java.sql.Timestamp;

public class CreatePriceJson {
    private int price;
    private String modifiedBy;
    private Timestamp modifiedTimestamp;

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Price getPrice() {
        return new Price(price, modifiedBy, modifiedTimestamp);
    }
}
