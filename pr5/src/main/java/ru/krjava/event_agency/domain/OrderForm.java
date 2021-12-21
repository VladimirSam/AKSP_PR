package ru.krjava.event_agency.domain;
import lombok.Data;


@Data
public class OrderForm {
    private long id;


    private String request;

    private long userid;

    public Order toOrder(User user){
        Order order = new Order();
        order.setRequest(request);
        order.setUserid(user.getId());
        return order;
    }
}