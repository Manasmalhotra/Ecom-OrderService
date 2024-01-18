package com.example.orderservice.models;

public enum PaymentMode {
    CARD("card"),
    NET_BANKING("netbanking"),
    WALLET("wallet"),
    EMI("emi"),
    UPI("upi");

    String value;

    PaymentMode(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
