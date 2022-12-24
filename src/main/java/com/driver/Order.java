package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        this.deliveryTime = integerTime(deliveryTime);
    }

    private int integerTime(String deliveryTime)
    {
        String[] hhmm = deliveryTime.split(":");
        int hh = Integer.parseInt(hhmm[0]);
        int mm = Integer.parseInt(hhmm[1]);
        return hh*60+mm;
    }
    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
