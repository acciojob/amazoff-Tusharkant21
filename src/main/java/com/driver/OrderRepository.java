package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    HashMap<String, DeliveryPartner> DeliveryPartnerMap = new HashMap<>();
    HashMap<String, Order> OrderMap = new HashMap<>();
    HashMap<String, List<String>> PairMap = new HashMap<>();

//Order Repository

    //add
    public void addOrderInRepository(Order order)
    {
        OrderMap.put(order.getId(), order);
    }

    // get
    public Order getOrderByIdService(String id)
    {
        return OrderMap.get(id);
    }

    // get all order
    public List<String> AllOrderService()
    {
        List<String> listOfOrder = new ArrayList<>();
        for(String orderId :OrderMap.keySet())
        {
            listOfOrder.add(orderId);
        }
        return listOfOrder;
    }

//DeliveryPartner

//add

    public void addDeliveryPartnerInRepository(String id)
    {
        DeliveryPartner deliveryPartner = new DeliveryPartner(id);
        DeliveryPartnerMap.put(id, deliveryPartner);
    }

    // get
    public DeliveryPartner getDeliveryPartnerDetailsById(String id)
    {
        return DeliveryPartnerMap.get(id);
    }

    //put

    public void putOrderPartnerInRepository(String partnerId, String orderId)
    {
        if(PairMap.containsKey(partnerId))
        {
            PairMap.get(partnerId).add(orderId);
        }
        else
        {
            List<String> list = new ArrayList<>();
            list.add(orderId);
            PairMap.put(partnerId,list);
        }
    }

    public int orderAssignByPartnerId(String id)
    {
        if(!PairMap.containsKey(id)) return 0;
        return PairMap.get(id).size();
    }

    public List<String> listOfOrderByPartnerId(String id)
    {
        if(!PairMap.containsKey(id))return new ArrayList<String>();
        return PairMap.get(id);
    }

    public int countOfOrdersNotAssignedToPartner()
    {
        int count =0;
        for(String partnerId:PairMap.keySet())
        {
            List<String> orderId = PairMap.get(partnerId);
            for(String id: orderId)
            {
                if(!OrderMap.containsKey(id))
                {
                    count++;
                }
            }
        }
        return count;
    }

//integer to String time formate

    private String stringTimeFormate(int minute)
    {
        int hours = minute/60;
        int mins = minute%60;
        String time = "";
        if(hours<9)
        {
            time += "0"+String.valueOf(hours)+":";
        }
        else
        {
            time+=String.valueOf(hours)+":";
        }
        if(mins<9)
        {
            time+= "0"+ String.valueOf(mins);
        }
        else
        {
            time+=String.valueOf(mins);
        }
        return time;
    }


    public String lastDeliveryMadeRepository(String id)
    {
        String time = "";
        List<String> lastDeliveryId= PairMap.get(id);

        String lastOrderId = lastDeliveryId.get(lastDeliveryId.size()-1);

        int minute = OrderMap.get(lastOrderId).getDeliveryTime();
        time = stringTimeFormate(minute);
        return time;
    }

    //Delete Partner and there respective orders from the repository

    public void deleteOrder(List<String> list)
    {
        for(String orderId :list)
        {
            OrderMap.remove(orderId);
        }
    }


    public void deletePartnerById(String partnerId)
    { if(!DeliveryPartnerMap.containsKey(partnerId)) return;
        DeliveryPartnerMap.remove(partnerId);
        PairMap.remove(partnerId);
        deleteOrder(PairMap.get(partnerId));
    }

    public void deleteOrderById(String id)
    {
        OrderMap.remove(id);
    }

    public int getOrdersLeftAfterGivenTimeByPartnerIdRepository(String time, String partnerid) {
        int count =0;
        int HHMMtime = integerTime(time);
        if(!PairMap.containsKey(partnerid)) return 0;
        else
        {
            List<String> list = PairMap.get(partnerid);
            for(String OrderId :list)
            {
                if(!OrderMap.containsKey(OrderId)) continue;
                int HHMMdeliverytime = OrderMap.get(OrderId).getDeliveryTime();
                if(HHMMdeliverytime>HHMMtime) count++;
            }
        }

        return count;
    }

    public int integerTime(String time)
    {
        String[] hhmm = time.split(":");
        int hh = Integer.parseInt(hhmm[0]);
        int mm = Integer.parseInt(hhmm[1]);
        return hh*60+mm;
    }

}
