package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderService {
    @Autowired OrderRepository orderRepository;

    //order
    public void addOrderService(Order order)
    {
        orderRepository.addOrderInRepository(order);
    }

    public Order getOrderById(String id)
    {
        return orderRepository.getOrderByIdService(id);
    }

    public List<String> listOfOrderService()
    {
        return orderRepository.AllOrderService();
    }

    //Deliver Partner

    public void addDeliveryDetailsService(String deliveryPartner)
    {
        orderRepository.addDeliveryPartnerInRepository(deliveryPartner);
    }
    public DeliveryPartner getDeliveryPartnerService(String id)
    {
        return orderRepository.getDeliveryPartnerDetailsById(id);
    }

    //pair

    public void pairOrderPartnerService(String orderid, String partnerid)
    {
        orderRepository.putOrderPartnerInRepository(partnerid, orderid);
    }

    public int orderAssignByPartnerIdService(String partnerid)
    {
        return orderRepository.orderAssignByPartnerId(partnerid);
    }
    public List<String> ListofordersByPartnerIdService(String id)
    {
        return orderRepository.listOfOrderByPartnerId(id);
    }
    public int getOrdersLeftAfterGivenTimeByPartnerIdService(String time , String partnerid)
    {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerIdRepository(time, partnerid);
    }

    //count

    public int countOfOrdersUnassignedToPartnerService(){
        return orderRepository.countOfOrdersNotAssignedToPartner();
    }
    public String lastOrderDeliveryTimeService(String id)
    {
        return orderRepository.lastDeliveryMadeRepository(id);
    }

    public void deletePartnerByIdService(String id)
    {
        orderRepository.deletePartnerById(id);
    }

    public void deleteOrderByIdService(String id)
    {
        orderRepository.deleteOrderById(id);
    }
}
