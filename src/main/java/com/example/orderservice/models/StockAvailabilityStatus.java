package com.example.orderservice.models;

public enum StockAvailabilityStatus {

    IN_STOCK("In-Stock"),
    OUT_OF_STOCK("OUT_OF_STOCK");
    private final String value;
    StockAvailabilityStatus(String value){
        this.value=value;
    }
}
